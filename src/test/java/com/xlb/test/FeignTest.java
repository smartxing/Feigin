package com.xlb.test;

import java.io.IOException;
import java.util.List;

import feign.Feign;
import feign.Logger;
import feign.Response;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;
import feign.gson.GsonDecoder;

public class FeignTest {

    public static void main(String[] args) {
        testFeign();
    }

    public static void testFeign() {
        try {
            IService service = Feign.builder().decoder(new GsonDecoder())
                .errorDecoder(new ServiceErrorDecoder(new GsonDecoder())).logger(new Logger.ErrorLogger())
                .logLevel(Logger.Level.BASIC).target(IService.class, "http://localhost:8080");

            List<Contributor> contributors = service.contributors("004");
            if (contributors != null && contributors.size() > 0) {
                for (Contributor contributor : contributors) {
                    System.out.println(contributor.getLogin() + " (" + contributor.getContributions() + ")");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    static class ServiceErrorDecoder implements ErrorDecoder {

        final Decoder      decoder;
        final ErrorDecoder defaultDecoder = new ErrorDecoder.Default();

        ServiceErrorDecoder(Decoder decoder) {
            this.decoder = decoder;
        }

        @Override
        public Exception decode(String methodKey, Response response) {
            try {
                return (Exception) decoder.decode(response, ServiceException.class);
            } catch (IOException fallbackToDefault) {
                return defaultDecoder.decode(methodKey, response);
            }
        }
    }
}
