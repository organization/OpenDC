package be.zvz.opendc.http

import be.zvz.kotlininside.httpinterface.HttpException
import be.zvz.kotlininside.httpinterface.HttpInterface
import be.zvz.kotlininside.json.JsonBrowser
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.StringRequestListener

class FANHttpInterface : HttpInterface {
    override fun upload(
        url: String,
        option: HttpInterface.Option?
    ): JsonBrowser? {
        val request = AndroidNetworking.upload(url)

        option?.let {
            request.addQueryParameter(it.queryParameter)
                .addPathParameter(it.pathParameter)
                .addHeaders(it.headers)
                .addMultipartFile(it.multipartFile, it.multipartContentType)
                .addMultipartFileList(it.multipartFileList, it.multipartContentType)
                .addMultipartParameter(it.multipartParameter, it.multipartContentType)
        }

        var json: JsonBrowser? = null

        request.build()
            .getAsString(object : StringRequestListener {
                override fun onResponse(response: String?) {
                    json = JsonBrowser.parse(response)
                }

                override fun onError(anError: ANError?) {
                    throw HttpException(anError!!.errorCode, anError.response.message())
                }

            })
        return json
    }

    override fun get(
        url: String,
        option: HttpInterface.Option?
    ): JsonBrowser? {
        val request = AndroidNetworking.get(url)

        option?.let {
            request.addQueryParameter(it.queryParameter)
                .addPathParameter(it.pathParameter)
                .addHeaders(it.headers)
        }

        var json: JsonBrowser? = null

        request.build()
            .getAsString(object : StringRequestListener {
                override fun onResponse(response: String?) {
                    json = JsonBrowser.parse(response)
                }

                override fun onError(anError: ANError?) {
                    throw HttpException(anError!!.errorCode, anError.response.message())
                }

            })
        return json
    }

    override fun post(
        url: String,
        option: HttpInterface.Option?
    ): JsonBrowser? {
        val request = AndroidNetworking.post(url)

        option?.let {
            request.addQueryParameter(it.queryParameter)
                .addPathParameter(it.pathParameter)
                .addHeaders(it.headers)
        }

        var json: JsonBrowser? = null

        request.build()
            .getAsString(object : StringRequestListener {
                override fun onResponse(response: String?) {
                    json = JsonBrowser.parse(response)
                }

                override fun onError(anError: ANError?) {
                    throw HttpException(anError!!.errorCode, anError.response.message())
                }

            })
        return json
    }

    override fun delete(
        url: String,
        option: HttpInterface.Option?
    ): JsonBrowser? {
        val request = AndroidNetworking.delete(url)

        option?.let {
            request.addQueryParameter(it.queryParameter)
                .addPathParameter(it.pathParameter)
                .addHeaders(it.headers)
        }

        var json: JsonBrowser? = null

        request.build()
            .getAsString(object : StringRequestListener {
                override fun onResponse(response: String?) {
                    json = JsonBrowser.parse(response)
                }

                override fun onError(anError: ANError?) {
                    throw HttpException(anError!!.errorCode, anError.response.message())
                }

            })
        return json
    }

    override fun head(
        url: String,
        option: HttpInterface.Option?
    ): JsonBrowser? {
        val request = AndroidNetworking.head(url)

        option?.let {
            request.addQueryParameter(it.queryParameter)
                .addPathParameter(it.pathParameter)
                .addHeaders(it.headers)
        }

        var json: JsonBrowser? = null

        request.build()
            .getAsString(object : StringRequestListener {
                override fun onResponse(response: String?) {
                    json = JsonBrowser.parse(response)
                }

                override fun onError(anError: ANError?) {
                    throw HttpException(anError!!.errorCode, anError.response.message())
                }

            })
        return json
    }

    override fun put(
        url: String,
        option: HttpInterface.Option?
    ): JsonBrowser? {
        val request = AndroidNetworking.put(url)

        option?.let {
            request.addQueryParameter(it.queryParameter)
                .addPathParameter(it.pathParameter)
                .addHeaders(it.headers)
        }

        var json: JsonBrowser? = null

        request.build()
            .getAsString(object : StringRequestListener {
                override fun onResponse(response: String?) {
                    json = JsonBrowser.parse(response)
                }

                override fun onError(anError: ANError?) {
                    throw HttpException(anError!!.errorCode, anError.response.message())
                }

            })
        return json
    }

    override fun patch(
        url: String,
        option: HttpInterface.Option?
    ): JsonBrowser? {
        val request = AndroidNetworking.patch(url)

        option?.let {
            request.addQueryParameter(it.queryParameter)
                .addPathParameter(it.pathParameter)
                .addHeaders(it.headers)
        }

        var json: JsonBrowser? = null

        request.build()
            .getAsString(object : StringRequestListener {
                override fun onResponse(response: String?) {
                    json = JsonBrowser.parse(response)
                }

                override fun onError(anError: ANError?) {
                    throw HttpException(anError!!.errorCode, anError.response.message())
                }

            })
        return json
    }

}