package com.vtnra.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.DialogFragment
import com.vtnetzwelt.web_client.R

const val dialogTag = "dialog-ui"

class BaseDialog(private val dialogTheme: Int, private val dialogTitle: String, private val dialogContent: String): DialogFragment() {

    lateinit var title : AppCompatTextView
    lateinit var content : AppCompatTextView
    lateinit var okTextView : AppCompatTextView
    private lateinit var rootView: View
    lateinit var cardView: CardView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rootView = inflater.inflate(R.layout.dialog, container, false)
        cardView = rootView.findViewById<View>(R.id.dialog_root) as CardView
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        title = rootView.findViewById(R.id.dialog_heading)
        content = rootView.findViewById(R.id.dialog_sub_heading)
        okTextView = rootView.findViewById(R.id.dialog_ok_button)

        setupDialog()
        onOkClicked()
    }

    private fun setupDialog() {
        title.text = dialogTitle
        content.text = dialogContent
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setStyle(STYLE_NO_TITLE, dialogTheme)
    }

    private fun onOkClicked() {
        okTextView.setOnClickListener {
            dismiss()
        }
    }
}
