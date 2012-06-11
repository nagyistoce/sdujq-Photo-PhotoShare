package com.android.tool;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;

import com.android.main.Handle_photo;
import com.android.main.R;

public class Handlers {
	private final static int[] mFrame1Res = new int[] {
			R.drawable.frame_around1_left_top, R.drawable.frame_around1_left,
			R.drawable.frame_around1_left_bottom,
			R.drawable.frame_around1_bottom,
			R.drawable.frame_around1_right_bottom,
			R.drawable.frame_around1_right, R.drawable.frame_around1_right_top,
			R.drawable.frame_around1_top };
	private final static int[] mFrame2Res = new int[] {
			R.drawable.frame_around2_left_top, R.drawable.frame_around2_left,
			R.drawable.frame_around2_left_bottom,
			R.drawable.frame_around2_bottom,
			R.drawable.frame_around2_right_bottom,
			R.drawable.frame_around2_right, R.drawable.frame_around2_right_top,
			R.drawable.frame_around2_top };

	/**
	 * 添加边框
	 * 
	 * @param bm
	 *            原图片
	 * @param res
	 *            边框资源
	 * @return
	 */
	public static Bitmap addBigFrame(Bitmap bm, int num) {
		Bitmap bitmap = null;
		if(num==3)
			bitmap= decodeBitmap(R.drawable.fra3_b);
		else if (num==4)
			bitmap= decodeBitmap(R.drawable.fra4);
		else if (num==5)
			bitmap= decodeBitmap(R.drawable.fra5);
		else if (num==6)
			bitmap= decodeBitmap(R.drawable.fra6);
		Drawable[] array = new Drawable[2];
		array[0] = new BitmapDrawable(bm);
		Bitmap b = resize(bitmap, bm.getWidth(), bm.getHeight());
		array[1] = new BitmapDrawable(b);
		LayerDrawable layer = new LayerDrawable(array);
		return drawableToBitmap(layer);
	}

	/**
	 * 图片缩放
	 * 
	 * @param bm
	 * @param w
	 * @param h
	 * @return
	 */
	public static Bitmap resize(Bitmap bm, int w, int h) {
		Bitmap BitmapOrg = bm;

		int width = BitmapOrg.getWidth();
		int height = BitmapOrg.getHeight();
		int newWidth = w;
		int newHeight = h;

		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;

		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		// if you want to rotate the Bitmap
		// matrix.postRotate(45);

		// recreate the new Bitmap
		Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0, width,
				height, matrix, true);
		return resizedBitmap;
	}

	// 获得圆角图片的方法
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {

		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
				.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return output;
	}

	// 获得带倒影的图片方法
	public static Bitmap createReflectionImageWithOrigin(Bitmap bitmap) {
		final int reflectionGap = 4;
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();

		Matrix matrix = new Matrix();
		matrix.preScale(1, -1);

		Bitmap reflectionImage = Bitmap.createBitmap(bitmap, 0, height / 2,
				width, height / 2, matrix, false);

		Bitmap bitmapWithReflection = Bitmap.createBitmap(width,
				(height + height / 2), Config.ARGB_8888);

		Canvas canvas = new Canvas(bitmapWithReflection);
		canvas.drawBitmap(bitmap, 0, 0, null);
		Paint deafalutPaint = new Paint();
		canvas
				.drawRect(0, height, width, height + reflectionGap,
						deafalutPaint);

		canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);

		Paint paint = new Paint();
		LinearGradient shader = new LinearGradient(0, bitmap.getHeight(), 0,
				bitmapWithReflection.getHeight() + reflectionGap, 0x70ffffff,
				0x00ffffff, TileMode.CLAMP);
		paint.setShader(shader);
		// Set the Transfer mode to be porter duff and destination in
		paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
		// Draw a rectangle using the paint with our linear gradient
		canvas.drawRect(0, height, width, bitmapWithReflection.getHeight()
				+ reflectionGap, paint);

		return bitmapWithReflection;
	}

	/**
	 * 怀旧效果
	 * 
	 * @param bmp
	 * @return
	 */
	public static Bitmap oldRemeber(Bitmap bmp) {
		// 速度测试
		long start = System.currentTimeMillis();
		int width = bmp.getWidth();
		int height = bmp.getHeight();
		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.RGB_565);
		int pixColor = 0;
		int pixR = 0;
		int pixG = 0;
		int pixB = 0;
		int newR = 0;
		int newG = 0;
		int newB = 0;
		int[] pixels = new int[width * height];
		bmp.getPixels(pixels, 0, width, 0, 0, width, height);
		for (int i = 0; i < height; i++) {
			for (int k = 0; k < width; k++) {
				pixColor = pixels[width * i + k];
				pixR = Color.red(pixColor);
				pixG = Color.green(pixColor);
				pixB = Color.blue(pixColor);
				newR = (int) (0.393 * pixR + 0.769 * pixG + 0.189 * pixB);
				newG = (int) (0.349 * pixR + 0.686 * pixG + 0.168 * pixB);
				newB = (int) (0.272 * pixR + 0.534 * pixG + 0.131 * pixB);
				int newColor = Color.argb(255, newR > 255 ? 255 : newR,
						newG > 255 ? 255 : newG, newB > 255 ? 255 : newB);
				pixels[width * i + k] = newColor;
			}
		}

		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		long end = System.currentTimeMillis();
		Log.d("may", "used time=" + (end - start));
		return bitmap;
	}

	/**
	 * 柔化效果(高斯模糊)
	 * 
	 * @param bmp
	 * @return
	 */
	public static Bitmap blurImageAmeliorate(Bitmap bmp) {
		long start = System.currentTimeMillis();
		// 高斯矩阵
		int[] gauss = new int[] { 1, 2, 1, 2, 4, 2, 1, 2, 1 };

		int width = bmp.getWidth();
		int height = bmp.getHeight();
		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.RGB_565);

		int pixR = 0;
		int pixG = 0;
		int pixB = 0;

		int pixColor = 0;

		int newR = 0;
		int newG = 0;
		int newB = 0;

		int delta = 16; // 值越小图片会越亮，越大则越暗

		int idx = 0;
		int[] pixels = new int[width * height];
		bmp.getPixels(pixels, 0, width, 0, 0, width, height);
		for (int i = 1, length = height - 1; i < length; i++) {
			for (int k = 1, len = width - 1; k < len; k++) {
				idx = 0;
				for (int m = -1; m <= 1; m++) {
					for (int n = -1; n <= 1; n++) {
						pixColor = pixels[(i + m) * width + k + n];
						pixR = Color.red(pixColor);
						pixG = Color.green(pixColor);
						pixB = Color.blue(pixColor);

						newR = newR + (int) (pixR * gauss[idx]);
						newG = newG + (int) (pixG * gauss[idx]);
						newB = newB + (int) (pixB * gauss[idx]);
						idx++;
					}
				}

				newR /= delta;
				newG /= delta;
				newB /= delta;

				newR = Math.min(255, Math.max(0, newR));
				newG = Math.min(255, Math.max(0, newG));
				newB = Math.min(255, Math.max(0, newB));

				pixels[i * width + k] = Color.argb(255, newR, newG, newB);

				newR = 0;
				newG = 0;
				newB = 0;
			}
		}

		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		long end = System.currentTimeMillis();
		Log.d("may", "used time=" + (end - start));
		return bitmap;
	}

	/**
	 * 图片锐化（拉普拉斯变换）
	 * 
	 * @param bmp
	 * @return
	 */
	public static Bitmap sharpenImageAmeliorate(Bitmap bmp) {
		long start = System.currentTimeMillis();
		// 拉普拉斯矩阵
		int[] laplacian = new int[] { -1, -1, -1, -1, 9, -1, -1, -1, -1 };

		int width = bmp.getWidth();
		int height = bmp.getHeight();
		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.RGB_565);

		int pixR = 0;
		int pixG = 0;
		int pixB = 0;

		int pixColor = 0;

		int newR = 0;
		int newG = 0;
		int newB = 0;

		int idx = 0;
		float alpha = 0.3F;
		int[] pixels = new int[width * height];
		bmp.getPixels(pixels, 0, width, 0, 0, width, height);
		for (int i = 1, length = height - 1; i < length; i++) {
			for (int k = 1, len = width - 1; k < len; k++) {
				idx = 0;
				for (int m = -1; m <= 1; m++) {
					for (int n = -1; n <= 1; n++) {
						pixColor = pixels[(i + n) * width + k + m];
						pixR = Color.red(pixColor);
						pixG = Color.green(pixColor);
						pixB = Color.blue(pixColor);

						newR = newR + (int) (pixR * laplacian[idx] * alpha);
						newG = newG + (int) (pixG * laplacian[idx] * alpha);
						newB = newB + (int) (pixB * laplacian[idx] * alpha);
						idx++;
					}
				}

				newR = Math.min(255, Math.max(0, newR));
				newG = Math.min(255, Math.max(0, newG));
				newB = Math.min(255, Math.max(0, newB));

				pixels[i * width + k] = Color.argb(255, newR, newG, newB);
				newR = 0;
				newG = 0;
				newB = 0;
			}
		}

		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		long end = System.currentTimeMillis();
		Log.d("may", "used time=" + (end - start));
		return bitmap;
	}

	/**
	 * 底片效果
	 * 
	 * @param bmp
	 * @return
	 */
	public static Bitmap film(Bitmap bmp) {
		// RGBA的最大值
		final int MAX_VALUE = 255;
		int width = bmp.getWidth();
		int height = bmp.getHeight();
		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.RGB_565);

		int pixR = 0;
		int pixG = 0;
		int pixB = 0;

		int pixColor = 0;

		int newR = 0;
		int newG = 0;
		int newB = 0;

		int[] pixels = new int[width * height];
		bmp.getPixels(pixels, 0, width, 0, 0, width, height);
		int pos = 0;
		for (int i = 1, length = height - 1; i < length; i++) {
			for (int k = 1, len = width - 1; k < len; k++) {
				pos = i * width + k;
				pixColor = pixels[pos];

				pixR = Color.red(pixColor);
				pixG = Color.green(pixColor);
				pixB = Color.blue(pixColor);

				newR = MAX_VALUE - pixR;
				newG = MAX_VALUE - pixG;
				newB = MAX_VALUE - pixB;

				newR = Math.min(MAX_VALUE, Math.max(0, newR));
				newG = Math.min(MAX_VALUE, Math.max(0, newG));
				newB = Math.min(MAX_VALUE, Math.max(0, newB));

				pixels[pos] = Color.argb(MAX_VALUE, newR, newG, newB);
			}
		}

		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		return bitmap;
	}

	/**
	 * 图片效果叠加
	 * 
	 * @param bmp
	 *            限制了尺寸大小的Bitmap
	 * @return
	 */
	public static Bitmap overlay(Bitmap bmp, Context con, int id) {
		long start = System.currentTimeMillis();
		int width = bmp.getWidth();
		int height = bmp.getHeight();
		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.RGB_565);

		// 对边框图片进行缩放
		Bitmap overlay = BitmapFactory.decodeResource(con.getResources(), id);
		int w = overlay.getWidth();
		int h = overlay.getHeight();
		float scaleX = width * 1F / w;
		float scaleY = height * 1F / h;
		Matrix matrix = new Matrix();
		matrix.postScale(scaleX, scaleY);

		Bitmap overlayCopy = Bitmap.createBitmap(overlay, 0, 0, w, h, matrix,
				true);

		int pixColor = 0;
		int layColor = 0;

		int pixR = 0;
		int pixG = 0;
		int pixB = 0;
		int pixA = 0;

		int newR = 0;
		int newG = 0;
		int newB = 0;
		int newA = 0;

		int layR = 0;
		int layG = 0;
		int layB = 0;
		int layA = 0;

		final float alpha = 0.5F;

		int[] srcPixels = new int[width * height];
		int[] layPixels = new int[width * height];
		bmp.getPixels(srcPixels, 0, width, 0, 0, width, height);
		overlayCopy.getPixels(layPixels, 0, width, 0, 0, width, height);

		int pos = 0;
		for (int i = 0; i < height; i++) {
			for (int k = 0; k < width; k++) {
				pos = i * width + k;
				pixColor = srcPixels[pos];
				layColor = layPixels[pos];

				pixR = Color.red(pixColor);
				pixG = Color.green(pixColor);
				pixB = Color.blue(pixColor);
				pixA = Color.alpha(pixColor);

				layR = Color.red(layColor);
				layG = Color.green(layColor);
				layB = Color.blue(layColor);
				layA = Color.alpha(layColor);

				newR = (int) (pixR * alpha + layR * (1 - alpha));
				newG = (int) (pixG * alpha + layG * (1 - alpha));
				newB = (int) (pixB * alpha + layB * (1 - alpha));
				layA = (int) (pixA * alpha + layA * (1 - alpha));

				newR = Math.min(255, Math.max(0, newR));
				newG = Math.min(255, Math.max(0, newG));
				newB = Math.min(255, Math.max(0, newB));
				newA = Math.min(255, Math.max(0, layA));

				srcPixels[pos] = Color.argb(newA, newR, newG, newB);
			}
		}

		bitmap.setPixels(srcPixels, 0, width, 0, 0, width, height);
		long end = System.currentTimeMillis();
		Log.d("may", "overlayAmeliorate used time=" + (end - start));
		return bitmap;
	}

	/**
	 * 光晕效果
	 * 
	 * @param bmp
	 * @param x
	 *            光晕中心点在bmp中的x坐标
	 * @param y
	 *            光晕中心点在bmp中的y坐标
	 * @param r
	 *            光晕的半径
	 * @return
	 */
	public static Bitmap halo(Bitmap bmp, int x, int y, float r) {
		long start = System.currentTimeMillis();
		// 高斯矩阵
		int[] gauss = new int[] { 1, 2, 1, 2, 4, 2, 1, 2, 1 };

		int width = bmp.getWidth();
		int height = bmp.getHeight();
		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.RGB_565);

		int pixR = 0;
		int pixG = 0;
		int pixB = 0;

		int pixColor = 0;

		int newR = 0;
		int newG = 0;
		int newB = 0;

		int delta = 18; // 值越小图片会越亮，越大则越暗

		int idx = 0;
		int[] pixels = new int[width * height];
		bmp.getPixels(pixels, 0, width, 0, 0, width, height);
		for (int i = 1, length = height - 1; i < length; i++) {
			for (int k = 1, len = width - 1; k < len; k++) {
				idx = 0;
				int distance = (int) (Math.pow(k - x, 2) + Math.pow(i - y, 2));
				// 不是中心区域的点做模糊处理
				if (distance > r * r) {
					for (int m = -1; m <= 1; m++) {
						for (int n = -1; n <= 1; n++) {
							pixColor = pixels[(i + m) * width + k + n];
							pixR = Color.red(pixColor);
							pixG = Color.green(pixColor);
							pixB = Color.blue(pixColor);

							newR = newR + (int) (pixR * gauss[idx]);
							newG = newG + (int) (pixG * gauss[idx]);
							newB = newB + (int) (pixB * gauss[idx]);
							idx++;
						}
					}

					newR /= delta;
					newG /= delta;
					newB /= delta;

					newR = Math.min(255, Math.max(0, newR));
					newG = Math.min(255, Math.max(0, newG));
					newB = Math.min(255, Math.max(0, newB));

					pixels[i * width + k] = Color.argb(255, newR, newG, newB);

					newR = 0;
					newG = 0;
					newB = 0;
				}
			}
		}

		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		long end = System.currentTimeMillis();
		Log.d("may", "used time=" + (end - start));
		return bitmap;
	}

	/** * 浮雕效果 * @param bmp * @return */
	public static Bitmap emboss(Bitmap bmp) {
		int width = bmp.getWidth();
		int height = bmp.getHeight();
		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.RGB_565);
		int pixR = 0;
		int pixG = 0;
		int pixB = 0;
		int pixColor = 0;
		int newR = 0;
		int newG = 0;
		int newB = 0;
		int[] pixels = new int[width * height];
		bmp.getPixels(pixels, 0, width, 0, 0, width, height);
		int pos = 0;
		for (int i = 1, length = height - 1; i < length; i++) {
			for (int k = 1, len = width - 1; k < len; k++) {
				pos = i * width + k;
				pixColor = pixels[pos];
				pixR = Color.red(pixColor);
				pixG = Color.green(pixColor);
				pixB = Color.blue(pixColor);
				pixColor = pixels[pos + 1];
				newR = Color.red(pixColor) - pixR + 127;
				newG = Color.green(pixColor) - pixG + 127;
				newB = Color.blue(pixColor) - pixB + 127;
				newR = Math.min(255, Math.max(0, newR));
				newG = Math.min(255, Math.max(0, newG));
				newB = Math.min(255, Math.max(0, newB));
				pixels[pos] = Color.argb(255, newR, newG, newB);
			}
		}
		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		return bitmap;
	}


	/**
	 * 图片旋转
	 * 
	 * @param b
	 * @param degrees
	 * @return
	 */
	public static Bitmap rotate(Bitmap b, int degrees) {
		if (degrees != 0 && b != null) {
			Matrix m = new Matrix();
			m.setRotate(degrees, (float) b.getWidth() / 2, (float) b
					.getHeight() / 2);
			try {
				Bitmap b2 = Bitmap.createBitmap(b, 0, 0, b.getWidth(), b
						.getHeight(), m, true);
				if (b != b2) {
					// b.recycle();
					b = b2;
				}
			} catch (OutOfMemoryError ex) {
			}
		}
		return b;
	}

	/**
	 * 图片与边框组合
	 * 
	 * @param bm
	 *            原图片
	 * @param res
	 *            边框资源
	 * @return
	 */
	public static Bitmap combinateFrame(Bitmap bm, int num) {
		int[] res = null;
		if (num == 1)
			res = mFrame1Res;
		else
			res = mFrame2Res;
		Bitmap bmp = decodeBitmap(res[0]);
		// 边框的宽高
		final int smallW = bmp.getWidth();
		final int smallH = bmp.getHeight();

		// 原图片的宽高
		final int bigW = bm.getWidth();
		final int bigH = bm.getHeight();

		int wCount = (int) Math.ceil(bigW * 1.0 / smallW);
		int hCount = (int) Math.ceil(bigH * 1.0 / smallH);

		// 组合后图片的宽高
		int newW = (wCount + 2) * smallW;
		int newH = (hCount + 2) * smallH;

		// 重新定义大小
		Bitmap newBitmap = Bitmap.createBitmap(newW, newH, Config.ARGB_8888);
		Canvas canvas = new Canvas(newBitmap);
		Paint p = new Paint();
		p.setColor(Color.TRANSPARENT);
		canvas.drawRect(new Rect(0, 0, newW, newH), p);

		Rect rect = new Rect(smallW, smallH, newW - smallW, newH - smallH);
		Paint paint = new Paint();
		paint.setColor(Color.WHITE);
		canvas.drawRect(rect, paint);

		// 绘原图
		canvas.drawBitmap(bm, (newW - bigW - 2 * smallW) / 2 + smallW, (newH
				- bigH - 2 * smallH)
				/ 2 + smallH, null);
		// 绘边框
		// 绘四个角
		int startW = newW - smallW;
		int startH = newH - smallH;
		Bitmap leftTopBm = decodeBitmap(res[0]); // 左上角
		Bitmap leftBottomBm = decodeBitmap(res[2]); // 左下角
		Bitmap rightBottomBm = decodeBitmap(res[4]); // 右下角
		Bitmap rightTopBm = decodeBitmap(res[6]); // 右上角

		canvas.drawBitmap(leftTopBm, 0, 0, null);
		canvas.drawBitmap(leftBottomBm, 0, startH, null);
		canvas.drawBitmap(rightBottomBm, startW, startH, null);
		canvas.drawBitmap(rightTopBm, startW, 0, null);

		leftTopBm.recycle();
		leftTopBm = null;
		leftBottomBm.recycle();
		leftBottomBm = null;
		rightBottomBm.recycle();
		rightBottomBm = null;
		rightTopBm.recycle();
		rightTopBm = null;

		// 绘左右边框
		Bitmap leftBm = decodeBitmap(res[1]);
		Bitmap rightBm = decodeBitmap(res[5]);
		for (int i = 0, length = hCount; i < length; i++) {
			int h = smallH * (i + 1);
			canvas.drawBitmap(leftBm, 0, h, null);
			canvas.drawBitmap(rightBm, startW, h, null);
		}

		leftBm.recycle();
		leftBm = null;
		rightBm.recycle();
		rightBm = null;

		// 绘上下边框
		Bitmap bottomBm = decodeBitmap(res[3]);
		Bitmap topBm = decodeBitmap(res[7]);
		for (int i = 0, length = wCount; i < length; i++) {
			int w = smallW * (i + 1);
			canvas.drawBitmap(bottomBm, w, startH, null);
			canvas.drawBitmap(topBm, w, 0, null);
		}

		bottomBm.recycle();
		bottomBm = null;
		topBm.recycle();
		topBm = null;

		canvas.save(Canvas.ALL_SAVE_FLAG);
		canvas.restore();

		return newBitmap;
	}

	/**
	 * 将R.drawable.*转换成Bitmap
	 * 
	 * @param res
	 * @return
	 */
	public static Bitmap decodeBitmap(int res) {
		return BitmapFactory.decodeResource(Handle_photo.h_context.getResources(), res);
	}

	/**
	 * 将Drawable转换成Bitmap
	 * 
	 * @param drawable
	 * @return
	 */
	public static Bitmap drawableToBitmap(Drawable drawable) {
		Bitmap bitmap = Bitmap
				.createBitmap(
						drawable.getIntrinsicWidth(),
						drawable.getIntrinsicHeight(),
						drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
								: Bitmap.Config.RGB_565);
		Canvas canvas = new Canvas(bitmap);
		// canvas.setBitmap(bitmap);
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable
				.getIntrinsicHeight());
		drawable.draw(canvas);
		return bitmap;
	}

}
