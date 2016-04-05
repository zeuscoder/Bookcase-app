package com.zeus.bookcase.app.cabinet.zxing.decoding;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.cabinet.ui.activity.AddBookActivity;
import com.zeus.bookcase.app.cabinet.ui.activity.CaptureActivity;
import com.zeus.bookcase.app.cabinet.ui.activity.MusicCardActivity;
import com.zeus.bookcase.app.cabinet.zxing.camera.CameraManager;
import com.zeus.bookcase.app.cabinet.zxing.view.ViewfinderResultPointCallback;

import java.util.Vector;

public final class CaptureActivityHandler extends Handler {

  @SuppressWarnings("unused")
private static final String TAG = CaptureActivityHandler.class.getSimpleName();

  private final CaptureActivity activity;
  private final DecodeThread decodeThread;
  private State state;


  private enum State {
    PREVIEW,
    SUCCESS,
    DONE
  }

  public CaptureActivityHandler(CaptureActivity activity, Vector<BarcodeFormat> decodeFormats,
      String characterSet) {
    this.activity = activity;
    decodeThread = new DecodeThread(activity, decodeFormats, characterSet,
        new ViewfinderResultPointCallback(activity.getViewfinderView()));
    decodeThread.start();
    state = State.SUCCESS;

    // Start ourselves capturing previews and decoding.
    CameraManager.get().startPreview();
    restartPreviewAndDecode();
  }

  @Override
  
  /**
   **	扫描结果的处理
   */
  public void handleMessage(Message message) {
    switch (message.what) {
      case R.id.auto_focus:
        if (state == State.PREVIEW) {
          CameraManager.get().requestAutoFocus(this, R.id.auto_focus);
        }
        break;
      case R.id.restart_preview:
        restartPreviewAndDecode();
        break;
      //扫描成功的消息
      case R.id.decode_succeeded:
        state = State.SUCCESS;

        Bundle bundle = message.getData();
        Bitmap barcode = bundle == null ? null :(Bitmap) bundle.getParcelable(DecodeThread.BARCODE_BITMAP);

        String str_result=((Result) message.obj).getText();
        activity.handleDecode((Result) message.obj, barcode);

        //获取ISDN码返回主Activity
    	Intent intent=new Intent(activity,AddBookActivity.class);
		intent.putExtra("result", str_result);
		activity.setResult(100,intent);
		activity.finish();
        break;
        
      case R.id.decode_failed:
        Log.i("OUTPUT", "Got return scan result message");
        state = State.PREVIEW;
        CameraManager.get().requestPreviewFrame(decodeThread.getHandler(), R.id.decode);
        break;
      case R.id.return_scan_result:
        Log.i("OUTPUT", "Got return scan result message");
    	Intent intent2=new Intent(activity,MusicCardActivity.class);
		activity.startActivity(intent2);
        break;
    }
  }

  public void quitSynchronously() {
    state = State.DONE;
    CameraManager.get().stopPreview();
    Message quit = Message.obtain(decodeThread.getHandler(), R.id.quit);
    quit.sendToTarget();
    try {
      decodeThread.join();
    } catch (InterruptedException e) {

    }


    removeMessages(R.id.decode_succeeded);

    removeMessages(R.id.decode_failed);
  }

  private void restartPreviewAndDecode() {
    if (state == State.SUCCESS) {
      state = State.PREVIEW;
      CameraManager.get().requestPreviewFrame(decodeThread.getHandler(), R.id.decode);
      CameraManager.get().requestAutoFocus(this, R.id.auto_focus);
      activity.drawViewfinder();
    }
  }

}
