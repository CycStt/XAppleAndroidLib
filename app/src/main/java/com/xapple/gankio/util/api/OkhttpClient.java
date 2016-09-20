package com.xapple.gankio.util.api;

/**
 * 项目名称：XAppleGankIO
 * 类描述：
 * 创建人：wengyiming
 * 创建时间：2016/8/26 13:48
 * 修改人：wengyiming
 * 修改时间：2016/8/26 13:48
 * 修改备注：
 */

import com.xapple.gankio.APP;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

public class OkhttpClient {
    private static final String TAG = "OkhttpClient";
    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            return originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control",
                            String.format("max-age=%d", 60))
                    .build();
        }
    };
    private static OkHttpClient sInstance;

    public static OkHttpClient getInstance() {
        if (sInstance == null) {
            synchronized (OkhttpClient.class) {
                if (sInstance == null) {
                    sInstance = new OkHttpClient();
                    //noinspection ConstantConditions
                    File cacheFile = new File(APP.getInstance().getCacheDir(), APP.getInstance().getApplicationContext().getExternalCacheDir().getPath());
                    Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
                    HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                        @Override public void log(String message) {
                            Timber.tag("OkHttp").e(message);
                        }
                    });
                    logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
                    logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                    sInstance = new OkHttpClient.Builder()
                            .addInterceptor(logging)
                            .retryOnConnectionFailure(true)
                            .connectTimeout(15, TimeUnit.SECONDS)
                            .cache(cache)
                            .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                            .build();

                }
            }
        }
        return sInstance;
    }



    public static void setCertificates(InputStream... certificates) throws KeyStoreException {
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509", "BC");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            int index = 0;
            for (InputStream certificate : certificates) {
                String certificateAlias = Integer.toString(index++);
                keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));
                try {
                    if (certificate != null)
                        certificate.close();
                } catch (IOException e) {
                }
            }
            SSLContext sslContext = SSLContext.getInstance("TLS");
            TrustManagerFactory trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);

            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(keyStore, "railtoolapi".toCharArray());
            sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), new SecureRandom()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
