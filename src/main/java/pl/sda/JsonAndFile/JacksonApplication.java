package pl.sda.JsonAndFile;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.util.Map;

//TODO uzycie jacksona
public class JacksonApplication {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
//        String json = "{\n" +
//                "    \"firstName\": \"Szymon\",\n" +
//                "    \"lastName\": \"Nowak\",\n" +
//                "    \"age\": 24,\n" +
//                "    \"phoneNumbers\": [\n" +
//                "      {\n" +
//                "        \"number\": \"123456789\",\n" +
//                "        \"name\": \"prywatny\"\n" +
//                "      },\n" +
//                "      {\n" +
//                "        \"number\": \"6758493024\",\n" +
//                "        \"name\": \"slubowy\"\n" +
//                "      }\n" +
//                "    ]\n" +
//                "\n" +
//                "  }";
//        User user = objectMapper.readValue(json, User.class);
//        System.out.println();
        String json2 = "{\n" +
                "  \"szymonNowak\": {\n" +
                "    \"phoneNumber\": \"12345\",\n" +
                "    \"blabla\": \"asdfksdf\"\n" +
                "  },\n" +
                "  \"janKowalski\": {\n" +
                "    \"phoneNumber\": \"675849\",\n" +
                "    \"blabla\": \"ala ma kota\"\n" +
                "  }\n" +
                "}";
        Map<String, User2> users= objectMapper.readValue(json2, new TypeReference<Map<String, User2>>(){});
        System.out.println();
    }
    public static class User2{
        private String phoneNumber;
        private String blabla;

        @JsonCreator
        public User2(@JsonProperty ("phoneNumber") String phoneNumber,
                     @JsonProperty ("blabla") String blabla) {
            this.phoneNumber = phoneNumber;
            this.blabla = blabla;
        }
    }


}

