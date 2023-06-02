package ru.savenkov.homework

import okhttp3.MediaType
import okhttp3.RequestBody
import okio.*

class ProgressRequestBody(
    private val requestBody: RequestBody,
    private val progressListener: ((Long, Long) -> Unit)?
) : RequestBody() {

    override fun contentType(): MediaType? {
        return requestBody.contentType()
    }

    override fun contentLength(): Long {
        return requestBody.contentLength()
    }

    override fun writeTo(sink: BufferedSink) {
        val countingSink = CountingSink(sink, progressListener, requestBody.contentLength())
        val bufferedSink = countingSink.buffer()
        requestBody.writeTo(bufferedSink)
        bufferedSink.flush()
    }

    private class CountingSink(
        sink: Sink,
        private val progressListener: ((Long, Long) -> Unit)?,
        private val length: Long
    ) : ForwardingSink(sink) {

        private var bytesWritten = 0L

        override fun write(source: Buffer, byteCount: Long) {
            super.write(source, byteCount)
            bytesWritten += byteCount
            progressListener?.invoke(bytesWritten, length)
        }
    }
}