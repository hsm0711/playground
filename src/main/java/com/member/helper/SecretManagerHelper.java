package com.member.helper;

import com.google.cloud.secretmanager.v1.AccessSecretVersionRequest;
import com.google.cloud.secretmanager.v1.AccessSecretVersionResponse;
import com.google.cloud.secretmanager.v1.SecretManagerServiceClient;
import com.member.exception.CustomException;
import lombok.experimental.UtilityClass;

/**
 * gradle.build에서 secret manager의 정보를 가져오기 위한 Helpler
 */
@UtilityClass
public class SecretManagerHelper {
  public static String retrieveSecretValue(String secretName) {
    try (SecretManagerServiceClient client = SecretManagerServiceClient.create()) {
      AccessSecretVersionRequest request = AccessSecretVersionRequest.newBuilder().setName(secretName).build();
      AccessSecretVersionResponse response = client.accessSecretVersion(request);
      return response.getPayload().getData().toStringUtf8();
    } catch (Exception e) {
      throw new CustomException("Error retrieving secret value");
    }
  }
}
