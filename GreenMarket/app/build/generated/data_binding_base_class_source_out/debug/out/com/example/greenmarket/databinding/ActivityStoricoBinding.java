// Generated by view binder compiler. Do not edit!
package com.example.greenmarket.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.greenmarket.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityStoricoBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ConstraintLayout activityStorico;

  @NonNull
  public final TextView storicoTxt;

  private ActivityStoricoBinding(@NonNull ConstraintLayout rootView,
      @NonNull ConstraintLayout activityStorico, @NonNull TextView storicoTxt) {
    this.rootView = rootView;
    this.activityStorico = activityStorico;
    this.storicoTxt = storicoTxt;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityStoricoBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityStoricoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_storico, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityStoricoBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      ConstraintLayout activityStorico = (ConstraintLayout) rootView;

      id = R.id.storico_txt;
      TextView storicoTxt = ViewBindings.findChildViewById(rootView, id);
      if (storicoTxt == null) {
        break missingId;
      }

      return new ActivityStoricoBinding((ConstraintLayout) rootView, activityStorico, storicoTxt);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
