package be.zvz.opendc.http

import android.util.Log
import be.zvz.kotlininside.http.HttpException
import be.zvz.kotlininside.http.HttpInterface
import be.zvz.kotlininside.json.JsonBrowser
import com.androidnetworking.AndroidNetworking

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
                .addMultipartParameter(it.multipartParameter, it.multipartContentType)
                .addMultipartFile(it.multipartFile, it.multipartContentType)
                .addMultipartFileList(it.multipartFileList, it.multipartContentType)

            it.userAgent?.let { userAgent ->
                request.setUserAgent(userAgent)
            }
        }

        val requestResult = request.build().executeForString()

        if (requestResult.isSuccess) {
            val result = requestResult.result.toString()
            Log.d("OpenDC", result)
            return JsonBrowser.parse(result)
        } else {
            throw HttpException(requestResult.error)
        }
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

            it.userAgent?.let { userAgent ->
                request.setUserAgent(userAgent)
            }
        }

        val requestResult = request.build().executeForString()

        if (requestResult.isSuccess) {
            return JsonBrowser.parse(requestResult.result.toString())
        } else {
            throw HttpException(requestResult.error)
        }
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

            it.userAgent?.let { userAgent ->
                request.setUserAgent(userAgent)
            }
        }

        val requestResult = request.build().executeForString()

        if (requestResult.isSuccess) {
            return JsonBrowser.parse(requestResult.result.toString())
        } else {
            throw HttpException(requestResult.error)
        }
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

            it.userAgent?.let { userAgent ->
                request.setUserAgent(userAgent)
            }
        }

        val requestResult = request.build().executeForString()

        if (requestResult.isSuccess) {
            return JsonBrowser.parse(requestResult.result.toString())
        } else {
            throw HttpException(requestResult.error)
        }
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

            it.userAgent?.let { userAgent ->
                request.setUserAgent(userAgent)
            }
        }

        val requestResult = request.build().executeForString()

        if (requestResult.isSuccess) {
            return JsonBrowser.parse(requestResult.result.toString())
        } else {
            throw HttpException(requestResult.error)
        }
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

            it.userAgent?.let { userAgent ->
                request.setUserAgent(userAgent)
            }
        }

        val requestResult = request.build().executeForString()

        if (requestResult.isSuccess) {
            return JsonBrowser.parse(requestResult.result.toString())
        } else {
            throw HttpException(requestResult.error)
        }
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

            it.userAgent?.let { userAgent ->
                request.setUserAgent(userAgent)
            }
        }

        val requestResult = request.build().executeForString()

        if (requestResult.isSuccess) {
            return JsonBrowser.parse(requestResult.result.toString())
        } else {
            throw HttpException(requestResult.error)
        }
    }

}