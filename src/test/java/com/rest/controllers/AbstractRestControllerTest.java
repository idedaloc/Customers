package com.rest.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author dedaloc2
 *
 */
public abstract class AbstractRestControllerTest {

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
