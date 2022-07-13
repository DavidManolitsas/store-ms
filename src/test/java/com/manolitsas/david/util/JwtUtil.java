package com.manolitsas.david.util;

import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class JwtUtil {

  private final RestTemplate restTemplate = new RestTemplate();

  public String getValidToken(
      String oauthUri, String clientId, String clientSecret, String audience) {
    try {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);

      JSONObject requestBody = new JSONObject();
      requestBody.put("client_id", clientId);
      requestBody.put("client_secret", clientSecret);
      requestBody.put("audience", audience);
      requestBody.put("grant_type", "client_credentials");
      log.info(requestBody.toString(2));

      HttpEntity<String> request = new HttpEntity<>(requestBody.toString(), headers);
      var response =
          restTemplate.exchange(oauthUri, HttpMethod.POST, request, TokenResponse.class).getBody();

      return response.getAccessToken();
    } catch (JSONException e) {
      e.printStackTrace();
      return null;
    }
  }

  private static HttpRequest.BodyPublisher buildRequestData(Map<Object, Object> data) {
    var builder = new StringBuilder();
    for (Map.Entry<Object, Object> entry : data.entrySet()) {
      if (builder.length() > 0) {
        builder.append("&");
      }
      builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
      builder.append("=");
      builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
    }

    return HttpRequest.BodyPublishers.ofString(builder.toString());
  }
}
