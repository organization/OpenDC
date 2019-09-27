package be.zvz.opendc.http

import be.zvz.kotlininside.httpinterface.HttpInterface
import be.zvz.kotlininside.json.JsonBrowser
import com.androidnetworking.AndroidNetworking

class FANHttpInterface : HttpInterface {
    override fun post(url: String?, data: MutableMap<String, String>?): JsonBrowser {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get(url: String?, data: MutableMap<String, String>?): JsonBrowser {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(url: String?, data: MutableMap<String, String>?): JsonBrowser {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun head(url: String?, data: MutableMap<String, String>?): JsonBrowser {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun put(url: String?, data: MutableMap<String, String>?): JsonBrowser {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun patch(url: String?, data: MutableMap<String, String>?): JsonBrowser {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setUserAgent(userAgent: String?): HttpInterface {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setHeader(key: String?, value: String?): HttpInterface {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setHeaders(data: MutableMap<String, String>?): HttpInterface {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getHeader(key: String?): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getHeaders(): MutableMap<String, String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addBodyParameter(key: String?, value: String?): HttpInterface {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addPathParameter(key: String?, value: String?): HttpInterface {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addQueryParameter(key: String?, value: String?): HttpInterface {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}