package com.example.vishavjit_harika.ui.home

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView

class ImageGridAdapter(private val context: Context, private val gridID: Int): BaseAdapter() {
    override fun getCount(): Int {
        return ImageGrids.getGridById(gridID)!!.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val mImageView: ImageView

        if (convertView == null) {
            mImageView = ImageView(context)
            mImageView.layoutParams = ViewGroup.LayoutParams(350, 350)
            mImageView.scaleType = ImageView.ScaleType.CENTER_CROP
        } else {
            mImageView = convertView as ImageView
        }

        mImageView.setImageResource(ImageGrids.getGridById(gridID)!![position])
        return mImageView
    }
}