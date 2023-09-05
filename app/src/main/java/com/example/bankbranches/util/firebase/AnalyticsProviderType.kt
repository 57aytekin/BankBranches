package com.example.bankbranches.util.firebase

import android.os.Bundle

interface AnalyticsProviderType{
    fun logEvent(eventName :String, params : Bundle)
}