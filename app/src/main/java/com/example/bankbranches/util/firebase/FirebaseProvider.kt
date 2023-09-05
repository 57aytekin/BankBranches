package com.example.bankbranches.util.firebase

import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

class FirebaseProvider(appContext : Context) : AnalyticsProviderType{

    var firebaseAnalytics = FirebaseAnalytics.getInstance(appContext)

    override fun logEvent(eventName: String, params: Bundle) {
        firebaseAnalytics.logEvent(eventName,params)
    }
}