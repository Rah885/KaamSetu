package com.rah885.kaamsetu.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rah885.kaamsetu.data.database.AppDataEntity
import com.rah885.kaamsetu.data.database.KaamSetuDatabase
import com.rah885.kaamsetu.ui.screens.customer.CustomerMainScreen
import com.rah885.kaamsetu.ui.screens.customer.PaymentData
import com.rah885.kaamsetu.ui.screens.customer.ServiceRequestData
import com.rah885.kaamsetu.ui.screens.home.HomeScreen
import com.rah885.kaamsetu.ui.screens.worker.WorkerMainScreen
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject

object AppRoutes {
    const val HOME = "home"
    const val CUSTOMER = "customer"
    const val WORKER = "worker"
}

private const val REQUESTS_KEY = "service_requests"
private const val PAYMENTS_KEY = "payments"

@Composable
fun AppNavigation() {

    val context = LocalContext.current

    val navController = rememberNavController()

    val database = remember {
        KaamSetuDatabase.getInstance(context)
    }

    val dao = remember {
        database.appDataDao()
    }

    val scope = rememberCoroutineScope()

    /*
     * Real permanent request data.
     */
    val serviceRequests = remember {
        mutableStateListOf<ServiceRequestData>()
    }

    /*
     * Real permanent payment history.
     */
    val payments = remember {
        mutableStateListOf<PaymentData>()
    }

    /*
     * Database loading indicator.
     */
    var dataLoaded by remember {
        mutableStateOf(false)
    }

    /*
     * App start होने पर Room से पुराना data load करें।
     */
    LaunchedEffect(Unit) {

        val savedRequests = dao.get(REQUESTS_KEY)

        if (savedRequests != null) {
            serviceRequests.clear()
            serviceRequests.addAll(
                requestsFromJson(savedRequests.value)
            )
        }

        val savedPayments = dao.get(PAYMENTS_KEY)

        if (savedPayments != null) {
            payments.clear()
            payments.addAll(
                paymentsFromJson(savedPayments.value)
            )
        }

        dataLoaded = true
    }

    /*
     * अभी database loading पूरी नहीं हुई तो
     * navigation को जल्दी शुरू नहीं करेंगे।
     */
    if (!dataLoaded) {
        return
    }

    /*
     * Requests को Room में permanent save करना।
     */
    fun saveRequests() {

        scope.launch {

            dao.save(
                AppDataEntity(
                    key = REQUESTS_KEY,
                    value = requestsToJson(serviceRequests)
                )
            )
        }
    }

    /*
     * Payments को Room में permanent save करना।
     */
    fun savePayments() {

        scope.launch {

            dao.save(
                AppDataEntity(
                    key = PAYMENTS_KEY,
                    value = paymentsToJson(payments)
                )
            )
        }
    }

    NavHost(
        navController = navController,
        startDestination = AppRoutes.HOME
    ) {

        composable(AppRoutes.HOME) {

            HomeScreen(
                onCustomerClick = {
                    navController.navigate(AppRoutes.CUSTOMER)
                },

                onWorkerClick = {
                    navController.navigate(AppRoutes.WORKER)
                }
            )
        }

        composable(AppRoutes.CUSTOMER) {

            CustomerMainScreen(

                submittedRequests = serviceRequests,

                onRequestSubmitted = { request ->

                    serviceRequests.add(request)

                    saveRequests()
                },

                onRequestUpdated = { updatedRequest ->

                    val index = serviceRequests.indexOfFirst {
                        it.id == updatedRequest.id
                    }

                    if (index >= 0) {

                        serviceRequests[index] = updatedRequest

                        saveRequests()
                    }
                },

                payments = payments,

                onPaymentSuccess = { payment ->

                    val existingPaymentIndex =
                        payments.indexOfFirst {
                            it.id == payment.id
                        }

                    if (existingPaymentIndex >= 0) {

                        payments[existingPaymentIndex] = payment

                    } else {

                        payments.add(payment)
                    }

                    savePayments()

                    val requestIndex =
                        serviceRequests.indexOfFirst {
                            it.id == payment.requestId
                        }

                    if (requestIndex >= 0) {

                        serviceRequests[requestIndex] =
                            serviceRequests[requestIndex].copy(
                                status = "भुगतान सफल"
                            )

                        saveRequests()
                    }
                }
            )
        }

        composable(AppRoutes.WORKER) {

            WorkerMainScreen(

                serviceRequests = serviceRequests,

                onRequestUpdated = { updatedRequest ->

                    val index = serviceRequests.indexOfFirst {
                        it.id == updatedRequest.id
                    }

                    if (index >= 0) {

                        serviceRequests[index] = updatedRequest

                        saveRequests()
                    }
                }
            )
        }
    }
}


/*
 * -----------------------------------------
 * REQUEST JSON STORAGE
 * -----------------------------------------
 */

private fun requestsToJson(
    requests: List<ServiceRequestData>
): String {

    val array = JSONArray()

    requests.forEach { request ->

        val objectData = JSONObject()

        objectData.put("id", request.id)
        objectData.put("service", request.service)
        objectData.put("workerName", request.workerName)
        objectData.put("customerName", request.customerName)
        objectData.put("mobile", request.mobile)
        objectData.put("description", request.description)
        objectData.put("location", request.location)
        objectData.put("dateTime", request.dateTime)
        objectData.put("status", request.status)
        objectData.put("price", request.price)
        objectData.put("rating", request.rating)
        objectData.put("review", request.review)

        array.put(objectData)
    }

    return array.toString()
}


private fun requestsFromJson(
    json: String
): List<ServiceRequestData> {

    val result = mutableListOf<ServiceRequestData>()

    if (json.isBlank()) {
        return result
    }

    try {

        val array = JSONArray(json)

        for (index in 0 until array.length()) {

            val objectData = array.getJSONObject(index)

            result.add(
                ServiceRequestData(

                    id = objectData.optLong("id"),

                    service = objectData.optString(
                        "service"
                    ),

                    workerName = objectData.optString(
                        "workerName"
                    ),

                    customerName = objectData.optString(
                        "customerName"
                    ),

                    mobile = objectData.optString(
                        "mobile"
                    ),

                    description = objectData.optString(
                        "description"
                    ),

                    location = objectData.optString(
                        "location"
                    ),

                    dateTime = objectData.optString(
                        "dateTime"
                    ),

                    status = objectData.optString(
                        "status",
                        "रिक्वेस्ट भेजी गई"
                    ),

                    price = objectData.optString(
                        "price"
                    ),

                    rating = objectData.optInt(
                        "rating",
                        0
                    ),

                    review = objectData.optString(
                        "review"
                    )
                )
            )
        }

    } catch (_: Exception) {

        /*
         * अगर पुराना data खराब हो तो
         * app crash नहीं होगा।
         */
    }

    return result
}


/*
 * -----------------------------------------
 * PAYMENT JSON STORAGE
 * -----------------------------------------
 */

private fun paymentsToJson(
    payments: List<PaymentData>
): String {

    val array = JSONArray()

    payments.forEach { payment ->

        val objectData = JSONObject()

        objectData.put("id", payment.id)
        objectData.put("requestId", payment.requestId)
        objectData.put("service", payment.service)
        objectData.put("workerName", payment.workerName)
        objectData.put("amount", payment.amount)
        objectData.put("dateTime", payment.dateTime)
        objectData.put("status", payment.status)

        array.put(objectData)
    }

    return array.toString()
}


private fun paymentsFromJson(
    json: String
): List<PaymentData> {

    val result = mutableListOf<PaymentData>()

    if (json.isBlank()) {
        return result
    }

    try {

        val array = JSONArray(json)

        for (index in 0 until array.length()) {

            val objectData = array.getJSONObject(index)

            result.add(
                PaymentData(

                    id = objectData.optLong("id"),

                    requestId = objectData.optLong(
                        "requestId"
                    ),

                    service = objectData.optString(
                        "service"
                    ),

                    workerName = objectData.optString(
                        "workerName"
                    ),

                    amount = objectData.optString(
                        "amount"
                    ),

                    dateTime = objectData.optString(
                        "dateTime"
                    ),

                    status = objectData.optString(
                        "status",
                        "भुगतान सफल"
                    )
                )
            )
        }

    } catch (_: Exception) {

        /*
         * खराब data की वजह से app crash नहीं होगा।
         */
    }

    return result
}
