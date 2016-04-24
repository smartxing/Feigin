package com.xlb.test;

import java.util.List;

import feign.Param;
import feign.RequestLine;

public interface IService {

    @RequestLine("GET /{owner}")
    List<Contributor> contributors(@Param("owner") String owner);
}
