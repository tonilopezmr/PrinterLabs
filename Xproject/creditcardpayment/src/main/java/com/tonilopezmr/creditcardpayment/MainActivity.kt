package com.tonilopezmr.creditcardpayment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.braintreepayments.api.BraintreePaymentActivity
import com.braintreepayments.api.PaymentRequest
import com.braintreepayments.api.models.PaymentMethodNonce
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.util.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun onBrainTreeSubmit(view: View) {
        val paymentRequest = PaymentRequest().clientToken("eyJ2ZXJzaW9uIjoyLCJhdXRob3JpemF0aW9uRmluZ2VycHJpbnQiOiI5Y2ZmMjExMGFjODQ4MWNmYTdlMTljMmY5NmZjMDdhOWZmYWVhOTAzZmIzYWZjODIyNzU4NWE4ODVhY2NmMGMxfGNyZWF0ZWRfYXQ9MjAxNi0xMC0wN1QyMDoxNzo0OS4xNDAxNzcxNjkrMDAwMFx1MDAyNm1lcmNoYW50X2lkPTM0OHBrOWNnZjNiZ3l3MmJcdTAwMjZwdWJsaWNfa2V5PTJuMjQ3ZHY4OWJxOXZtcHIiLCJjb25maWdVcmwiOiJodHRwczovL2FwaS5zYW5kYm94LmJyYWludHJlZWdhdGV3YXkuY29tOjQ0My9tZXJjaGFudHMvMzQ4cGs5Y2dmM2JneXcyYi9jbGllbnRfYXBpL3YxL2NvbmZpZ3VyYXRpb24iLCJjaGFsbGVuZ2VzIjpbXSwiZW52aXJvbm1lbnQiOiJzYW5kYm94IiwiY2xpZW50QXBpVXJsIjoiaHR0cHM6Ly9hcGkuc2FuZGJveC5icmFpbnRyZWVnYXRld2F5LmNvbTo0NDMvbWVyY2hhbnRzLzM0OHBrOWNnZjNiZ3l3MmIvY2xpZW50X2FwaSIsImFzc2V0c1VybCI6Imh0dHBzOi8vYXNzZXRzLmJyYWludHJlZWdhdGV3YXkuY29tIiwiYXV0aFVybCI6Imh0dHBzOi8vYXV0aC52ZW5tby5zYW5kYm94LmJyYWludHJlZWdhdGV3YXkuY29tIiwiYW5hbHl0aWNzIjp7InVybCI6Imh0dHBzOi8vY2xpZW50LWFuYWx5dGljcy5zYW5kYm94LmJyYWludHJlZWdhdGV3YXkuY29tLzM0OHBrOWNnZjNiZ3l3MmIifSwidGhyZWVEU2VjdXJlRW5hYmxlZCI6dHJ1ZSwicGF5cGFsRW5hYmxlZCI6dHJ1ZSwicGF5cGFsIjp7ImRpc3BsYXlOYW1lIjoiQWNtZSBXaWRnZXRzLCBMdGQuIChTYW5kYm94KSIsImNsaWVudElkIjpudWxsLCJwcml2YWN5VXJsIjoiaHR0cDovL2V4YW1wbGUuY29tL3BwIiwidXNlckFncmVlbWVudFVybCI6Imh0dHA6Ly9leGFtcGxlLmNvbS90b3MiLCJiYXNlVXJsIjoiaHR0cHM6Ly9hc3NldHMuYnJhaW50cmVlZ2F0ZXdheS5jb20iLCJhc3NldHNVcmwiOiJodHRwczovL2NoZWNrb3V0LnBheXBhbC5jb20iLCJkaXJlY3RCYXNlVXJsIjpudWxsLCJhbGxvd0h0dHAiOnRydWUsImVudmlyb25tZW50Tm9OZXR3b3JrIjp0cnVlLCJlbnZpcm9ubWVudCI6Im9mZmxpbmUiLCJ1bnZldHRlZE1lcmNoYW50IjpmYWxzZSwiYnJhaW50cmVlQ2xpZW50SWQiOiJtYXN0ZXJjbGllbnQzIiwiYmlsbGluZ0FncmVlbWVudHNFbmFibGVkIjp0cnVlLCJtZXJjaGFudEFjY291bnRJZCI6ImFjbWV3aWRnZXRzbHRkc2FuZGJveCIsImN1cnJlbmN5SXNvQ29kZSI6IlVTRCJ9LCJjb2luYmFzZUVuYWJsZWQiOmZhbHNlLCJtZXJjaGFudElkIjoiMzQ4cGs5Y2dmM2JneXcyYiIsInZlbm1vIjoib2ZmIn0=")
        val REQUEST_CODE = 23
        startActivityForResult(paymentRequest.getIntent(this), REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        val REQUEST_CODE = 23
        if (requestCode == REQUEST_CODE) {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    val paymentMethodNonce : PaymentMethodNonce = data.getParcelableExtra(
                            BraintreePaymentActivity.EXTRA_PAYMENT_METHOD_NONCE)

//                    val nonce = paymentMethodNonce.nonce
                    val nonce = "fake-valid-nonce"

                    Thread(Runnable {
                        val okhttp : OkHttpClient = OkHttpClient()
                        val parse = MediaType.parse("application/json; charset=utf-8")
                        val customer = "{ \"id\": \"${getNewId()}\", \"firstName\": \"Android\", \"lastName\": \"LastName\", \"phone\": \"23423\", \"email\": \"email@email.com\" }"
                        val json = " { \"amount\": \"100.50\", \"payment_method_nonce\": \"$nonce\", \"customer\": $customer, \"storeInVault\": true } "
                        Log.e("PAY", json)
                        val bodyRequest = RequestBody.create(parse, json)

                        val request = Request.Builder()
                                .url("http://10.0.2.2:3000/checkout/new")
                                .post(bodyRequest)
                                .build()

                        val response = okhttp.newCall(request).execute()

                        this@MainActivity.runOnUiThread({
                            informationTextView.text = response.body().string()
                        })
                    }).start()

                }
                BraintreePaymentActivity.BRAINTREE_RESULT_DEVELOPER_ERROR,
                BraintreePaymentActivity.BRAINTREE_RESULT_SERVER_ERROR,
                BraintreePaymentActivity.BRAINTREE_RESULT_SERVER_UNAVAILABLE -> {
                }
                else -> {
                }
            }// handle errors here, a throwable may be available in
            // data.getSerializableExtra(BraintreePaymentActivity.EXTRA_ERROR_MESSAGE)
        }
    }

    private fun getNewId(): String {
        return Random().nextInt(99999999).toString()
    }
}
