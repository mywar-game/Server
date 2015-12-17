package com.fantingame.game.gamecenter.partener.amigo;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONStyle;

import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.lang3.StringUtils;

import com.fantingame.game.gamecenter.partener.amigo.RSASignature;


public class PayUtil {

	public static String wrapCreateOrder(Order order, String privateKey, String deliverType)
			throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException,
			IOException {
		JSONObject jsonReq = new JSONObject();
		String expireTime = order.getExpireTime();
		String notifyURL = order.getNotifyURL();

		StringBuilder signContent = new StringBuilder();
		signContent.append(order.getApiKey());
		jsonReq.put("api_key", order.getApiKey());

		signContent.append(order.getDealPrice());
		jsonReq.put("deal_price", order.getDealPrice().toString());
		signContent.append(deliverType);
		jsonReq.put("deliver_type", deliverType);

		if (!StringUtils.isBlank(expireTime)) {
			signContent.append(expireTime);
			jsonReq.put("expire_time", expireTime);
		}

		if (!StringUtils.isBlank(notifyURL)) {
			signContent.append(notifyURL);
			jsonReq.put("notify_url", notifyURL);
		}

		signContent.append(order.getOutOrderNo());
		jsonReq.put("out_order_no", order.getOutOrderNo());
		signContent.append(order.getSubject());
		jsonReq.put("subject", order.getSubject());
		signContent.append(order.getSubmitTime());
		jsonReq.put("submit_time", order.getSubmitTime());
		signContent.append(order.getTotalFee());
		jsonReq.put("total_fee", order.getTotalFee().toString());

		String sign = RSASignature.sign(signContent.toString(), privateKey, CharEncoding.UTF_8);
		jsonReq.put("sign", sign);

		// player_id不参与签名
		jsonReq.put("player_id", order.getPlayerId());

		return jsonReq.toJSONString(JSONStyle.NO_COMPRESS);
	}
}
