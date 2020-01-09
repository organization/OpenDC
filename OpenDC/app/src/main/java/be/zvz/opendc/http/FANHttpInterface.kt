package be.zvz.opendc.http

import be.zvz.kotlininside.http.HttpException
import be.zvz.kotlininside.http.HttpInterface
import be.zvz.kotlininside.json.JsonBrowser
import com.androidnetworking.AndroidNetworking
import org.mozilla.gecko.util.IOUtils
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream

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

            request.addMultipartParameter(it.multipartParameter)

            it.multipartFile.forEach { (key, value) ->
                val file = File.createTempFile("fan-upload-temp", ".tmp")

                FileOutputStream(file).use { fos ->
                    BufferedOutputStream(fos).use { bof ->
                        IOUtils.copy(value, bof)
                    }
                }

                request.addMultipartFile(key, file, it.multipartContentType)
            }

            it.multipartFileList.forEach { (key, value) ->
                value.forEach { `is` ->
                    val file = File.createTempFile("fan-upload-temp", ".tmp")

                    FileOutputStream(file).use { fos ->
                        BufferedOutputStream(fos).use { bof ->
                            IOUtils.copy(`is`, bof)
                        }
                    }

                    request.addMultipartFile(key, file, it.multipartContentType)
                }
            }

            it.userAgent?.let { userAgent ->
                request.setUserAgent(userAgent)
            }
        }

        val requestResult = request.build().executeForString()

        if (requestResult.isSuccess) {
            val result = requestResult.result.toString()
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