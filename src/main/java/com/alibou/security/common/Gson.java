package com.alibou.security.common;

import com.google.gson.*;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.io.*;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Gson {
    public static com.google.gson.Gson gson() {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new JsonDeserializer<>() {
                    @Override
                    public Object deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                        return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
                    }
                })
                .registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
                    @Override
                    public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
                        return LocalDateTime.parse(json.getAsJsonPrimitive().getAsString());
                    }
                })
                .create();
    }

//    public static void main(String[] args) {
//        String xmlDefault = "<!--<root>\n" +
//                "  <obtActivity id=\"1\" name=\"Vương Giả Chiến lần 9\" timeinfo=\"Không\" limitinfo=\"Server mở đủ 7 ngày\\nNgười chơi lv trên 70.\" ruleinfo=\"Tỉ thí Lôi đài với người chơi cùng cụm, chọn ra Vương giả Thế Giới.\" showtime=\"20140508100000\" viewTime=\"20140508100000\" starEnd=\"20140501100000,20140514235959\" disappearTime=\"20140531235959\" door=\"1\" activityName=\"conquest\" townId=\"\"type=\"1\" />\n" +
//                "  <obtActivity id=\"2\" name=\"Quay về Vương giả\" timeinfo=\"Không\" limitinfo=\"Server mở đủ 7 ngày\\nNgười chơi lv trên 60.\" ruleinfo=\"Căn cứ vào số ngày offline nhận quà phản hồi khác nhau.\" showtime=\"20140508100000\" viewTime=\"20140508100000\" starEnd=\"20140501100000,20140510235959\" disappearTime=\"20140531235959\" door=\"1\" activityName=\"oldplayerreturn\" townId=\"\"type=\"1\" />\n" +
//                "  <obtActivity id=\"3\" name=\"Khuyến mãi nạp thẻ\" timeinfo=\"Không\" limitinfo=\"Người chơi tất cả server đều được tham gia\" ruleinfo=\"Trong t.g HĐ, nạp vàng được khuyến mại, nạp nhiều lần khuyến mại nhiều lần.\" showtime=\"20140508100000\" viewTime=\"20140508100000\" starEnd=\"20140508100000,20140514235959\" disappearTime=\"20140531235959\" door=\"0\" activityName=\"rechargeValue\" townId=\"\"type=\"1\" />\n" +
//                "  <obtActivity id=\"4\" name=\"HĐ hỏi đáp Sinh nhật\" timeinfo=\"Không\" limitinfo=\"Người chơi tất cả server đều được tham gia\" ruleinfo=\"Trong t.g HĐ, vào Thị trấn Carnival Moby Dick, tiến hành hỏi đáp với Sengoku, mỗi ngày có 3 lần hỏi đáp, trả lời đúng được thưởng. (Mất mạng thì tự động thoát khỏi Thị trấn Carnival)\" showtime=\"20140508100000\" viewTime=\"20140508100000\" starEnd=\"20140508100000,20140531235959\" disappearTime=\"20140531235959\" door=\"1\" activityName=\"goToTown\" townId=\"19\"type=\"1\" />\n" +
//                "  <obtActivity id=\"5\" name=\"Thị trấn Carnival tặng phúc lợi\" timeinfo=\"Không\" limitinfo=\"Người chơi tất cả server đều được tham gia\" ruleinfo=\"Trong t.g HĐ, vào Thị trấn Carnival Moby Dick sinh nhật, đối thoại với Sengoku nhận quà điểm danh sinh nhật. (Mất mạng thì tự động thoát khỏi Thị trấn Carnival)\" showtime=\"20140508100000\" viewTime=\"20140508100000\" starEnd=\"20140508100000,20140531235959\" disappearTime=\"20140531235959\" door=\"1\" activityName=\"goToTown\" townId=\"19\"type=\"1\" />\n" +
//                "  <obtActivity id=\"6\" name=\"Vòng quay Danh vọng\" timeinfo=\"Không\" limitinfo=\"Server mở đủ 7 ngày\" ruleinfo=\"Trong t.g HĐ, người chơi có thể vào Vòng quay Danh vọng, nhận Danh vọng\" showtime=\"20140508100000\" viewTime=\"20140508100000\" starEnd=\"20140508100000,20140514235959\" disappearTime=\"20140531235959\" door=\"1\" activityName=\"prestigeRoulette\" townId=\"\"type=\"1\" />\n" +
//                "  <obtActivity id=\"7\" name=\"Vòng quay Exp\" timeinfo=\"Không\" limitinfo=\"Server mở đủ 7 ngày\" ruleinfo=\"Trong t.g HĐ, người chơi có thể vào Vòng quay Exp, nhận Exp thăng cấp\" showtime=\"20140508100000\" viewTime=\"20140508100000\" starEnd=\"20140508100000,20140514235959\" disappearTime=\"20140531235959\" door=\"1\" activityName=\"roulette\" townId=\"\"type=\"1\" />\n" +
//                "  <obtActivity id=\"8\" name=\"Đào vàng Marineford\" timeinfo=\"Không\" limitinfo=\"Server mở đủ 14 ngày\\nNgười chơi lv trên 50\" ruleinfo=\"Trong t.g HĐ, người chơi tiêu 20 vàng ấn vào Bản đồ Kho báu đào vàng 1 lần, nhận p.thưởng.\" showtime=\"20140508100000\" viewTime=\"20140508100000\" starEnd=\"20140508100000,20140516235959\" disappearTime=\"20140531235959\" door=\"1\" activityName=\"dig\" townId=\"\"type=\"1\" />\n" +
//                "  <obtActivity id=\"9\" name=\"Tổ đội đi cướp\" timeinfo=\"Không\" limitinfo=\"Người chơi tất cả server đều được tham gia\" ruleinfo=\"P.thưởng đi cướp người chơi nhân đôi x5, p.thưởng NPC gấp 2 lần, tầm bảo đi cướp và Phụ bản tổ đội nhiều người có x.suất nhận p.thưởng siêu giá trị.\" showtime=\"20140508100000\" viewTime=\"20140508100000\" starEnd=\"20140508100000,20140514235959\" disappearTime=\"20140531235959\" door=\"0\" activityName=\"\" townId=\"\"type=\"1\" />\n" +
//                "  <obtActivity id=\"10\" name=\"Ra khơi vui vẻ\" timeinfo=\"Không\" limitinfo=\"Người chơi tất cả server đều được tham gia\" ruleinfo=\"Beri nhận được khi ra khơi và nấu ăn tăng 50%\" showtime=\"20140508100000\" viewTime=\"20140508100000\" starEnd=\"20140508100000,20140514235959\" disappearTime=\"20140531235959\" door=\"1\" activityName=\"niceSail\" townId=\"\"type=\"1\" />\n" +
//                "  <obtActivity id=\"11\" name=\"Vòng quay may mắn\" timeinfo=\"Không\" limitinfo=\"Server mở đủ 14 ngày\" ruleinfo=\"Trong t.g HĐ, người chơi có thể Quay vòng quay, nhận p.thưởng. (Trong t.g HĐ, kích hoạt toàn bộ Tinh Linh tàu được 30 lần quay miễn phí)\" showtime=\"20140517100000\" viewTime=\"20140517100000\" starEnd=\"20140517100000,20140523235959\" disappearTime=\"20140531235959\" door=\"0\" activityName=\"lotteryPoint\" townId=\"\"type=\"1\" />\n" +
//                "  <obtActivity id=\"12\" name=\"Tập kích Black Pearl\" timeinfo=\"Không\" limitinfo=\"Người chơi tất cả server đều được tham gia\" ruleinfo=\"Mỗi ngày ngẫu nhiên xuất hiện 4 con tàu Black Pearl trở về, cướp thắng lợi có thưởng, thất bại dùng 10 vàng mua thắng lợi. Mỗi con tàu người chơi chỉ được cướp 1 lần.\" showtime=\"20140517100000\" viewTime=\"20140517100000\" starEnd=\"20140517100000,20140523235959\" disappearTime=\"20140531235959\" door=\"0\" activityName=\"blackpearl\" townId=\"\"type=\"1\" />\n" +
//                "  <obtActivity id=\"13\" name=\"Cuộc chiến bảo vệ Hải tặc\" timeinfo=\"Không\" limitinfo=\"Server mở đủ 14 ngày\" ruleinfo=\"Trong t.g HĐ, 19:00-19:09, 19:10-19:19, 19:20-19:30 hàng ngày tại các thị trấn ngẫu nhiên sẽ xuất hiện Quân Hải Tặc, tham gia là có thưởng, tiêu diệt được p.thưởng hậu hĩ.\" showtime=\"20140517100000\" viewTime=\"20140517100000\" starEnd=\"20140517100000,20140523235959\" disappearTime=\"20140531235959\" door=\"0\" activityName=\"\" townId=\"\"type=\"1\" />\n" +
//                "  <obtActivity id=\"14\" name=\"Lễ bao thời hạn\" timeinfo=\"Không\" limitinfo=\"Người chơi tất cả server đều được tham gia\" ruleinfo=\"Trong t.g HĐ, chỉ cần nạp vàng, là có thể nhận quà thời hạn siêu giá trị.\" showtime=\"20140517100000\" viewTime=\"20140517100000\" starEnd=\"20140517100000,20140519235959\" disappearTime=\"20140531235959\" door=\"0\" activityName=\"limitCharge\" townId=\"\"type=\"1\" />\n" +
//                "  <obtActivity id=\"15\" name=\"Tinh Linh chúc phúc\" timeinfo=\"Không\" limitinfo=\"Người chơi tất cả server đều được tham gia\" ruleinfo=\"Trong t.g HĐ, người chơi chiếm mỏ được Tinh linh chúc phúc sẽ được thêm p.thưởng. (8:00 -10:00 mỗi tối Tinh linh sẽ ngẫu nhiên xuất hiện 4 lần,  mỗi lần kéo dài 10 phút; trong thời gian Tinh Linh xuất hiện,  chiếm mỏ được Tinh Linh chúc phúc sẽ không mất Lực HĐ, kéo dài thời gian sẽ mất; Khi Tinh Linh biến mất người chơi chiếm mỏ này sẽ được thêm p.thưởng; P.thưởng gồm: 25 vàng, 500 D.vọng, Túi quà tím random*2, Bánh mì*2.)\" showtime=\"20140517100000\" viewTime=\"20140517100000\" starEnd=\"20140517100000,20140519235959\" disappearTime=\"20140531235959\" door=\"0\" activityName=\"resbattle\" townId=\"\"type=\"1\" />\n" +
//                "  <obtActivity id=\"16\" name=\"Quỹ Hải Tặc\" timeinfo=\"Không\" limitinfo=\"Server mở đủ 14 ngày\" ruleinfo=\"Mua quỹ Hải tặc, lãi suất siêu lớn.\" showtime=\"20140524100000\" viewTime=\"20140524100000\" starEnd=\"20140524100000,20140529235959\" disappearTime=\"20140531235959\" door=\"1\" activityName=\"layin\" townId=\"\"type=\"1\" />\n" +
//                "  <obtActivity id=\"17\" name=\"Giảm giá\" timeinfo=\"Không\" limitinfo=\"Server mở đủ 14 ngày\" ruleinfo=\"Trong t.g HĐ, Thương thành thời hạn mỗi ngày sẽ giảm giá các loại đạo cụ, muốn mua hãy nhanh lên.\" showtime=\"20140524100000\" viewTime=\"20140524100000\" starEnd=\"20140524100000,20140530235959\" disappearTime=\"20140531235959\" door=\"1\" activityName=\"limitBuy\" townId=\"\"type=\"1\" />\n" +
//                "  <obtActivity id=\"18\" name=\"Tích lũy tiêu vàng\" timeinfo=\"Không\" limitinfo=\"Server mở đủ 14 ngày\" ruleinfo=\"Trong t.g HĐ, tích lũy tiêu vàng nhận quà, thêm Pet sinh nhật “Megalo”.\" showtime=\"20140524100000\" viewTime=\"20140524100000\" starEnd=\"20140524100000,20140530235959\" disappearTime=\"20140531235959\" door=\"0\" activityName=\"consumption\" townId=\"\"type=\"1\" />\n" +
//                "  <obtActivity id=\"19\" name=\"Thu thập thẻ bài\" timeinfo=\"Không\" limitinfo=\"Server mở đủ 14 ngày\" ruleinfo=\"Tấn công PB có thể rơi thẻ bài, thu thập bộ bài đổi thưởng.\" showtime=\"20140524100000\" viewTime=\"20140524100000\" starEnd=\"20140524100000,20140530235959\" disappearTime=\"20140531235959\" door=\"1\" activityName=\"cardactivity\" townId=\"\"type=\"1\" />\n" +
//                "  <obtActivity id=\"20\" name=\"Thách đấu Đấu Trường\" timeinfo=\"Không\" limitinfo=\"Người chơi tất cả server đều được tham gia\" ruleinfo=\"Trong t.g HĐ, Thách đấu Đấu trường có x.suất nhận đạo cụ.\" showtime=\"20140524100000\" viewTime=\"20140524100000\" starEnd=\"20140524100000,20140526235959\" disappearTime=\"20140531235959\" door=\"0\" activityName=\"\" townId=\"\"type=\"1\" />\n" +
//                "  <obtActivity id=\"22\" name=\"Cây Ác Ma\" timeinfo=\"Không\" limitinfo=\"Server mở trên 7 ngày\\nNgười chơi lv trên 60.\" ruleinfo=\"Tưới nước, bón phân, quyên góp VP cho Cây Ác Ma có thể giúp cây nhỏ trưởng thành, nhận được quà Trái Ác Ma.\" showtime=\"20140525100000\" viewTime=\"20140525100000\" starEnd=\"20140525100000,20140531235959\" disappearTime=\"20140531235959\" door=\"1\" activityName=\"daimonapple\" townId=\"\"type=\"1\" />\n" +
//                "  <obtActivity id=\"23\" name=\"Vui vẻ online\" timeinfo=\"Không\" limitinfo=\"Người chơi tất cả server đều được tham gia\" ruleinfo=\"Trong t.g HĐ, thứ 7, CN hàng tuần có nhận thưởng tương ứng với thời gian online tích lũy.\" showtime=\"20140508100000\" viewTime=\"20140508100000\" starEnd=\"20140501100000,20140531235959\" disappearTime=\"20140531235959\" door=\"1\" activityName=\"summeronlineprize\" townId=\"\"type=\"1\" />\n" +
//                "</root>-->\n";
//        String filePath = "C:\\Users\\ADMIN\\Desktop\\phattu_backend\\phattu_backend\\src\\main\\java\\com\\alibou\\security\\common\\obtActivity.xml";
//        try {
//            // Đọc nội dung của file XML
//            File inputFile = new File(filePath);
//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder = factory.newDocumentBuilder();
//            Document doc = builder.parse(inputFile);
//
//            // Lấy thẻ root
//            Element root = doc.getDocumentElement();
//
//            // Chuyển tất cả các node trong root thành chuỗi
//            String xmlString = "";
//            StringWriter sw = new StringWriter();
//            TransformerFactory tfRead = TransformerFactory.newInstance();
//            Transformer transformerRead = tfRead.newTransformer();
//            transformerRead.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
//            transformerRead.transform(new DOMSource(root), new StreamResult(sw));
//            xmlString = sw.toString();
//
//            // Tách từng dòng trong chuỗi
//            String[] lines = xmlString.split("\\r?\\n");
//            String modifiedXmlString = "<root>\n";
//            // Lấy từng dòng và xử lý
//            for (int i = 1; i < lines.length - 1; i++) {
//                modifiedXmlString += lines[i] + "\n";
//            }
//            modifiedXmlString += "</root>";
//
//            // Chuyển chuỗi thành node
//            DocumentBuilderFactory factory2 = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder2 = factory2.newDocumentBuilder();
//            Document doc2 = builder2.parse(new InputSource(new StringReader(modifiedXmlString)));
//
//            // Lưu lại nội dung mới vào file
//            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
//                TransformerFactory tf = TransformerFactory.newInstance();
//                Transformer transformer = tf.newTransformer();
//
//
//                transformer.transform(new DOMSource(doc2), new StreamResult(writer));
//            }
//
//            System.out.println("Đã thực hiện chuyển đổi và lưu lại file XML thành công.");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
