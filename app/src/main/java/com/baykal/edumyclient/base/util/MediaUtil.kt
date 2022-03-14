package com.baykal.edumyclient.base.util

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Environment.*
import android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
import android.provider.MediaStore.MediaColumns.*
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

class MediaUtil {
    companion object {

        fun getBitmapUri(context: Context, bitmap: Bitmap?): Uri? {
            return bitmap?.let {
                    val filename = "${System.currentTimeMillis()}.png"
                    val fos: OutputStream?
                    val contentValues = ContentValues().apply {
                        put(DISPLAY_NAME, filename)
                        put(MIME_TYPE, "image/png")
                        put(RELATIVE_PATH, DIRECTORY_DCIM)
                        put(IS_PENDING, 1)
                    }

                    val contentResolver = context.contentResolver
                    val uri = contentResolver.insert(EXTERNAL_CONTENT_URI, contentValues)
                    uri?.let { contentResolver.openOutputStream(it) }.also { fos = it }
                    fos?.use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }
                    fos?.flush()
                    fos?.close()

                    contentValues.clear()
                    contentValues.put(IS_PENDING, 0)
                    uri?.let {
                        contentResolver.update(it, contentValues, null, null)
                    }
                    uri
            } ?: run {
                null
            }
        }

        fun legacySave(context: Context, bitmap: Bitmap?): Uri? {
            return bitmap?.let {
                val filename = "${System.currentTimeMillis()}.png"
                val directory = getExternalStoragePublicDirectory(DIRECTORY_PICTURES)
                val file = File(directory, filename)
                val outStream = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream)
                outStream.flush()
                outStream.close()
                MediaScannerConnection.scanFile(
                    context, arrayOf(file.absolutePath),
                    null, null
                )
                FileProvider.getUriForFile(
                    context, "${context.packageName}.provider",
                    file
                )
            } ?: run {
                null
            }
        }
    }
}