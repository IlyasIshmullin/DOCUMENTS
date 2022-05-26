package com.example.documents_rewrite.mainApplication.app.documents

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import com.example.documents_rewrite.mainApplication.app.documents.docsLOgic.RealPathUtil
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream


fun Fragment.openDocumentPicker() {
    val mimeTypes = arrayListOf("image/*","application/pdf","application/msword")
    val openDocumentIntent = Intent(Intent.ACTION_GET_CONTENT).apply {
        addCategory(Intent.CATEGORY_OPENABLE)
        type = "*/*"
        putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
    }

    startActivityForResult(openDocumentIntent, OPEN_DOCUMENT_REQUEST_CODE)
}

const val OPEN_DOCUMENT_REQUEST_CODE = 2

fun Fragment.tryHandleOpenDocumentResult(requestCode: Int, resultCode: Int, data: Intent?): OpenFileResult {
    return if (requestCode == OPEN_DOCUMENT_REQUEST_CODE) {
        handleOpenDocumentResult(resultCode, data)
    } else OpenFileResult.DifferentResult
}

private fun Fragment.handleOpenDocumentResult(resultCode: Int, data: Intent?): OpenFileResult {
    return if (resultCode == Activity.RESULT_OK && data != null) {
        val contentUri = data.data
        if (contentUri != null) {
            val stream =
                try {
                    requireActivity().application.contentResolver.openInputStream(contentUri)
                } catch (exception: FileNotFoundException) {
                    return OpenFileResult.ErrorOpeningFile
                }
            val fileName = requireContext().contentResolver.queryFileName(contentUri)

            val stringPath = RealPathUtil.getRealPath(context, contentUri)

            if (stream != null && fileName != null) {

                // TODO: picking document from data uri
                OpenFileResult.FileWasOpened(fileName, stream, stringPath)
            } else OpenFileResult.ErrorOpeningFile
        } else {
            OpenFileResult.ErrorOpeningFile
        }
    } else {
        OpenFileResult.OpenFileWasCancelled
    }
}/*
fun convertMediaUriToPath(uri: Uri?): String? {
    val proj = arrayOf(MediaStore.Images.Media.DATA)
    val cursor: Cursor = getContentResolver().query(uri, proj, null, null, null)
    val column_index: Int = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
    cursor.moveToFirst()
    val path: String = cursor.getString(column_index)
    cursor.close()
    return path
}
*/
sealed class OpenFileResult {
    object OpenFileWasCancelled : OpenFileResult()
    data class FileWasOpened(val fileName: String, val content: InputStream, val Path: String) : OpenFileResult()
    object ErrorOpeningFile : OpenFileResult()
    object DifferentResult : OpenFileResult()
}