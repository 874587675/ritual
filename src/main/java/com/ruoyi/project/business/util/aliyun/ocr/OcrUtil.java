package com.ruoyi.project.business.util.aliyun.ocr;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.sdk.service.ocr_api20210707.AsyncClient;
import com.aliyun.sdk.service.ocr_api20210707.models.RecognizeIdcardRequest;
import com.aliyun.sdk.service.ocr_api20210707.models.RecognizeIdcardResponse;
import com.google.gson.Gson;
import com.ruoyi.project.business.util.config.AliyunConfig;
import darabonba.core.client.ClientOverrideConfiguration;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
public class OcrUtil {
    @Resource
    private AliyunConfig aliyunConfig;

    /**
     * 根据身份证照片识别信息
     *
     * @param idCard
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public JSONObject idCardORC(String idCard) throws ExecutionException, InterruptedException {
        StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()
                .accessKeyId(aliyunConfig.getAccessparams().getKeyId())
                .accessKeySecret(aliyunConfig.getAccessparams().getKeySecret())
                .build());
        AsyncClient client = AsyncClient.builder()
                .region(aliyunConfig.getOcrparams().getRegionId())
                .credentialsProvider(provider)
                .overrideConfiguration(ClientOverrideConfiguration.create().setEndpointOverride(aliyunConfig.getOcrparams().getEndpoint()))
                .build();
        RecognizeIdcardRequest recognizeIdcardRequest = RecognizeIdcardRequest.builder()
                .url(idCard)
                .build();
        CompletableFuture<RecognizeIdcardResponse> response = client.recognizeIdcard(recognizeIdcardRequest);
        RecognizeIdcardResponse resp = response.get();
        String json = new Gson().toJson(resp);

        // 解析 json 字符串为 JSONObject
        JSONObject object = JSON.parseObject(json);
        // 获取 body 部分 里面的用户基本信息
        JSONObject body = object.getJSONObject("body").getJSONObject("data").getJSONObject("data").getJSONObject("face").getJSONObject("data");
        client.close();
        return body;
    }
    
}
