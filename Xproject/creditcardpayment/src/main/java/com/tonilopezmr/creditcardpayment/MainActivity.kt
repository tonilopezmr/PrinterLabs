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
        val paymentRequest = PaymentRequest().clientToken("TOKEN_MOLON")
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
