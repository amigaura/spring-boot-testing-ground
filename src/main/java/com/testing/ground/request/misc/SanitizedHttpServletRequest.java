package com.testing.ground.request.misc;

import com.testing.ground.util.InputSanitizer;
import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class SanitizedHttpServletRequest extends HttpServletRequestWrapper {

    Logger LOGGER = org.slf4j.LoggerFactory.getLogger(SanitizedHttpServletRequest.class);
    private final String sanitizedBody;

    public SanitizedHttpServletRequest(HttpServletRequest request) throws IOException {
        super(request);
        String rawBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        LOGGER.debug("Raw body: {}", rawBody);
        this.sanitizedBody = InputSanitizer.sanitizeJson(rawBody);
        LOGGER.debug("Sanitized body: {}", sanitizedBody);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        byte[] bytes = sanitizedBody.getBytes(StandardCharsets.UTF_8);
        ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);

        return new ServletInputStream() {
            public boolean isFinished() { return byteStream.available() == 0; }
            public boolean isReady() { return true; }
            public void setReadListener(ReadListener listener) {}
            public int read() throws IOException { return byteStream.read(); }
        };
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }
}

