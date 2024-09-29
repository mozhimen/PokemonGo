package com.mozhimen.pokemongo.now

import android.content.Context
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.os.StrictMode.VmPolicy
import androidx.startup.Initializer
import com.mozhimen.kotlin.utilk.BuildConfig
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/11
 *     desc  :
 * </pre>
 */
class PokemonGo_Initializer : Initializer<Unit> {
    override fun create(context: Context) {
        if (!BuildConfig.DEBUG) {
            return
        }
        StrictMode.setThreadPolicy(
            ThreadPolicy.Builder().detectAll().penaltyLog().build()
        )
        StrictMode.setVmPolicy(VmPolicy.Builder().detectAll().penaltyLog().build())
        Timber.plant(DebugTree())
        return
    }

    override fun dependencies(): List<Class<out Initializer<*>>> =
        listOf()
}