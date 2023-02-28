package go.travel.dnh.service;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import go.travel.dnh.domain.member.MemberDTO;
import go.travel.dnh.domain.pay.PayDTO;
import go.travel.dnh.domain.pay.RefundDTO;
import go.travel.dnh.domain.reservation.AirReservationListDTO;
import go.travel.dnh.domain.reservation.ReservationDTO;
import go.travel.dnh.repository.PaymentRepository;
import go.travel.dnh.repository.ReservationRepository;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{

    @Value("${imp_key}")
    private String impKey;

    @Value("${imp_secret}")
    private String impSecret;

    @ToString
    @Getter
    private class Response{
        private PaymentInfo response;
    }

    @ToString
    @Getter
    private class PaymentInfo{
        private int amount;
    }

    private final PaymentRepository paymentRepository;
    private final ReservationRepository reservationRepository;


    @Override
    public String getToken() throws IOException {
        HttpsURLConnection conn = null;

        URL url = new URL("https://api.iamport.kr/users/getToken");

        conn = (HttpsURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);
        JsonObject json = new JsonObject();

        json.addProperty("imp_key", impKey);
        json.addProperty("imp_secret", impSecret);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));

        bw.write(json.toString());
        bw.flush();
        bw.close();

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

        Gson gson = new Gson();

        String response = gson.fromJson(br.readLine(), Map.class).get("response").toString();

        System.out.println(response);

        String token = gson.fromJson(response, Map.class).get("access_token").toString();

        br.close();
        conn.disconnect();

        return token;
    }

    @Override
    public int paymentInfo(String imp_uid, String access_token) throws IOException {
        HttpsURLConnection conn = null;

        URL url = new URL("https://api.iamport.kr/payments/" + imp_uid);

        conn = (HttpsURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", access_token);
        conn.setDoOutput(true);

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

        Gson gson = new Gson();

        Response response = gson.fromJson(br.readLine(), Response.class);

        br.close();
        conn.disconnect();

        return response.getResponse().getAmount();
    }

    @Override
    public void paymentCancle(String access_token, String imp_uid, int amount, String reason) throws IOException{
        System.out.println("결제 취소");

        System.out.println(access_token);

        System.out.println(imp_uid);

        HttpsURLConnection conn = null;
        URL url = new URL("https://api.iamport.kr/payments/cancel");

        conn = (HttpsURLConnection) url.openConnection();

        conn.setRequestMethod("POST");

        conn.setRequestProperty("Content-type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("Authorization", access_token);

        conn.setDoOutput(true);

        JsonObject json = new JsonObject();

        json.addProperty("reason", reason);
        json.addProperty("imp_uid", imp_uid);
        json.addProperty("amount", amount);
        json.addProperty("checksum", amount);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));

        bw.write(json.toString());
        bw.flush();
        bw.close();

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

        br.close();
        conn.disconnect();
    }

    @Override
    @Transactional
    public void insertPay(String imp_uid,Long merchant_uid,int totalPrice, String pay_method) {

        AirReservationListDTO dto = reservationRepository.getReservation(merchant_uid);
        System.out.println(dto);

        PayDTO payDTO = new PayDTO();
        payDTO.setPno(imp_uid);
        payDTO.setRno(dto.getRno());
        payDTO.setMno(dto.getMno());
        payDTO.setPay_tot_price(totalPrice);
        payDTO.setPay_method(pay_method);

        System.out.println(payDTO);
        paymentRepository.insertPay(payDTO);
        reservationRepository.update(dto.getRno());
    }

    @Override
    public List<PayDTO> readPayList() {
        return paymentRepository.readPayList();
    }

    @Override
    public PayDTO readPay(String pno) {
        return paymentRepository.readPay(pno);
    }

    @Override
    @Transactional
    public int refund(String pno, String rf_reason) {
        //pno로 db에 결제완료된 리스트가 있는지 확인하고 있으면 pay테이블은 update
        //그리고 refund테이블에 Insert

        PayDTO payDTO = paymentRepository.readPay(pno);

        if(payDTO!=null){
            paymentRepository.updatePayState(pno);

            RefundDTO refundDTO = new RefundDTO();
            refundDTO.setRfno(payDTO.getPno());
            refundDTO.setPno(payDTO.getPno());
            refundDTO.setMno(payDTO.getMno());
            refundDTO.setRf_reason(rf_reason);

            paymentRepository.insertRefund(refundDTO);
        }
        return 0;
    }

    @Override
    public int readOneRefund(String pno) {
        return paymentRepository.readOneRefund(pno);
    }

    @Override
    public String readPno(Long rno){
        return paymentRepository.readPno(rno);
    }

}
