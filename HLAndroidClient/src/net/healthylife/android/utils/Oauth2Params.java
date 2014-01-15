package net.healthylife.android.utils;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential.AccessMethod;

/**
 * 
 * oauth 2 parameters
 *
 */
public class Oauth2Params {

    private String mClientId;
	private String mClientSecret;
	private String mScope;
	private String mRederictUri;
	private String mUserId;
	private String mApiUrl;

	private String mTokenServerUrl;
	private String mAuthorizationServerEncodedUrl;
	
	private AccessMethod mAccessMethodccessMethod;
	
	/**
	 * 
	 * @param clientId
	 * @param clientSecret
	 * @param tokenServerUrl
	 * @param authorizationServerEncodedUrl
	 * @param accessMethod
	 * @param scope
	 * @param rederictUri
	 * @param userId
	 * @param apiUrl
	 */
	public Oauth2Params(String clientId, String clientSecret, String tokenServerUrl,
			String authorizationServerEncodedUrl, AccessMethod accessMethod,
			String scope, String rederictUri, String userId, String apiUrl) {
		mClientId=clientId;
		mClientSecret=clientSecret;
		mTokenServerUrl=tokenServerUrl;
		mAuthorizationServerEncodedUrl=authorizationServerEncodedUrl;
		if (accessMethod == null)
			mAccessMethodccessMethod=BearerToken.authorizationHeaderAccessMethod();
		else
			mAccessMethodccessMethod = accessMethod;
		mScope=scope;
		mRederictUri=rederictUri;
		mUserId=userId;
		mApiUrl=apiUrl;
	}
	
	public String getClientId() {
		if (this.mClientId==null || this.mClientId.length()==0) {
			throw new IllegalArgumentException("Please provide a valid mClientId in the Oauth2Params class");
		}
		return mClientId;
	}
	public String getClientSecret() {
		if (this.mClientSecret==null || this.mClientSecret.length()==0) {
			throw new IllegalArgumentException("Please provide a valid mClientSecret in the Oauth2Params class");
		}
		return mClientSecret;
	}
	
	public String getScope() {
		return mScope;
	}
	public String getRederictUri() {
		return mRederictUri;
	}
	public String getApiUrl() {
		return mApiUrl;
	}
	public String getTokenServerUrl() {
		return mTokenServerUrl;
	}

	public String getAuthorizationServerEncodedUrl() {
		return mAuthorizationServerEncodedUrl;
	}
	
	public AccessMethod getAccessMethod() {
		return mAccessMethodccessMethod;
	}
	
	public String getUserId() {
		return mUserId;
	}
}
