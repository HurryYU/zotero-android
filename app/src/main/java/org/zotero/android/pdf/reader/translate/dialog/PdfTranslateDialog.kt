package org.zotero.android.pdf.reader.translate.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import org.zotero.android.databinding.DialogPdfTranslateBinding

class PdfTranslateDialog : DialogFragment() {
    private lateinit var binding: DialogPdfTranslateBinding
    private var sourceText = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogPdfTranslateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvSourceText.text = sourceText
    }

    fun setSourceText(text: String) {
        this.sourceText = text
    }

    fun setTranslateResult(result: String) {
        binding.pb.visibility = View.GONE
        binding.tvTargetText.text = result
        binding.tvTargetText.visibility = View.VISIBLE
    }

    override fun onStart() {
        super.onStart()
        val win = dialog?.window
        win?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val params = win?.attributes
        params?.gravity = Gravity.CENTER
        params?.width = ViewGroup.LayoutParams.MATCH_PARENT
        params?.height = ViewGroup.LayoutParams.WRAP_CONTENT
        win?.attributes = params
    }
}