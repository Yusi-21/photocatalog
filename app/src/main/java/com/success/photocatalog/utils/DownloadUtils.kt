package com.success.photocatalog.utils

import android.content.Context
import android.media.MediaScannerConnection
import android.os.Environment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.IOException
import java.io.File
import java.io.FileOutputStream

object DownloadUtils {
    private val client = OkHttpClient.Builder()
        .followRedirects(true)
        .build()

    suspend fun downloadPhoto(
        context: Context,
        url: String,
        fileName: String
    ) : Result<File> = withContext(Dispatchers.IO) {
        try {
            val request = Request.Builder()
                .url(url)
                .get()
                .build()

            val response = client.newCall(request).execute()

            if (!response.isSuccessful) {
                return@withContext Result.failure(IOException("HTTP ${response.code}"))
            }

            val responseBody = response.body ?: return@withContext Result.failure(
                IOException("Empty Response")
            )

            val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            if (!downloadsDir.exists()) {
                downloadsDir.mkdirs()
            }

            val file = File(downloadsDir, fileName)

            FileOutputStream(file).use { outputStream ->
                responseBody.byteStream().use { inputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
            MediaScannerConnection.scanFile(
                context,
                arrayOf(file.absolutePath),
                null,
                null
            )

            Result.success(file)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}