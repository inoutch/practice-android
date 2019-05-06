package io.github.inoutch.practice.android.util

import android.content.ContentResolver
import android.net.Uri
import android.provider.MediaStore
import java.io.File

class NetUtil {
    companion object {
        fun convertToFile(contentResolver: ContentResolver, uri: Uri): File? {
            val projection = arrayOf(MediaStore.MediaColumns.DATA)
            return contentResolver
                    .query(uri, projection, null, null, null)
                    ?.use {
                        if (it.moveToFirst()) {
                            it.getString(0)?.let { x -> File(x) }
                        } else null
                    }
        }
    }
}
