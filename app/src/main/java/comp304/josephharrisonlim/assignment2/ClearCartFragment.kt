package comp304.josephharrisonlim.assignment2

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment

class ClearCartFragment : DialogFragment() {

    internal lateinit var mListener: NoticeDialogListener

    interface NoticeDialogListener {
        fun onDialogPositiveClick(dialog: DialogFragment)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            mListener = context as NoticeDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException((context.toString() +
                    " must implement NoticeDialogListener"))
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.clear_cart_confirmation)
                    .setPositiveButton(R.string.confirm) { _, _ ->
                        mListener.onDialogPositiveClick(this)
                    }
                    .setNegativeButton(R.string.cancel) { _, _ ->
                        mListener.onDialogNegativeClick(this)
                    }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
