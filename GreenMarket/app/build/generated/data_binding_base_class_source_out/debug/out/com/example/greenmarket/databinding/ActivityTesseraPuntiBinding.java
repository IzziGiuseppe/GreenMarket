// Generated by view binder compiler. Do not edit!
package com.example.greenmarket.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.greenmarket.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityTesseraPuntiBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ConstraintLayout activityTessPunti;

  @NonNull
  public final TextView codiciSconto;

  @NonNull
  public final Button converti;

  @NonNull
  public final TextView haiSpeso;

  @NonNull
  public final ImageButton infoCS;

  @NonNull
  public final TextView punti;

  @NonNull
  public final Button riscatta;

  @NonNull
  public final RecyclerView rvCodiciSconto;

  @NonNull
  public final TextView tiPremia;

  @NonNull
  public final TextView valorePunti;

  @NonNull
  public final TextView valoreSpesa;

  @NonNull
  public final View view;

  @NonNull
  public final View view2;

  @NonNull
  public final View view3;

  @NonNull
  public final View view4;

  private ActivityTesseraPuntiBinding(@NonNull ConstraintLayout rootView,
      @NonNull ConstraintLayout activityTessPunti, @NonNull TextView codiciSconto,
      @NonNull Button converti, @NonNull TextView haiSpeso, @NonNull ImageButton infoCS,
      @NonNull TextView punti, @NonNull Button riscatta, @NonNull RecyclerView rvCodiciSconto,
      @NonNull TextView tiPremia, @NonNull TextView valorePunti, @NonNull TextView valoreSpesa,
      @NonNull View view, @NonNull View view2, @NonNull View view3, @NonNull View view4) {
    this.rootView = rootView;
    this.activityTessPunti = activityTessPunti;
    this.codiciSconto = codiciSconto;
    this.converti = converti;
    this.haiSpeso = haiSpeso;
    this.infoCS = infoCS;
    this.punti = punti;
    this.riscatta = riscatta;
    this.rvCodiciSconto = rvCodiciSconto;
    this.tiPremia = tiPremia;
    this.valorePunti = valorePunti;
    this.valoreSpesa = valoreSpesa;
    this.view = view;
    this.view2 = view2;
    this.view3 = view3;
    this.view4 = view4;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityTesseraPuntiBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityTesseraPuntiBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_tessera_punti, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityTesseraPuntiBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      ConstraintLayout activityTessPunti = (ConstraintLayout) rootView;

      id = R.id.codici_sconto;
      TextView codiciSconto = ViewBindings.findChildViewById(rootView, id);
      if (codiciSconto == null) {
        break missingId;
      }

      id = R.id.converti;
      Button converti = ViewBindings.findChildViewById(rootView, id);
      if (converti == null) {
        break missingId;
      }

      id = R.id.hai_speso;
      TextView haiSpeso = ViewBindings.findChildViewById(rootView, id);
      if (haiSpeso == null) {
        break missingId;
      }

      id = R.id.infoCS;
      ImageButton infoCS = ViewBindings.findChildViewById(rootView, id);
      if (infoCS == null) {
        break missingId;
      }

      id = R.id.punti;
      TextView punti = ViewBindings.findChildViewById(rootView, id);
      if (punti == null) {
        break missingId;
      }

      id = R.id.riscatta;
      Button riscatta = ViewBindings.findChildViewById(rootView, id);
      if (riscatta == null) {
        break missingId;
      }

      id = R.id.rv_codici_sconto;
      RecyclerView rvCodiciSconto = ViewBindings.findChildViewById(rootView, id);
      if (rvCodiciSconto == null) {
        break missingId;
      }

      id = R.id.ti_premia;
      TextView tiPremia = ViewBindings.findChildViewById(rootView, id);
      if (tiPremia == null) {
        break missingId;
      }

      id = R.id.valore_punti;
      TextView valorePunti = ViewBindings.findChildViewById(rootView, id);
      if (valorePunti == null) {
        break missingId;
      }

      id = R.id.valore_spesa;
      TextView valoreSpesa = ViewBindings.findChildViewById(rootView, id);
      if (valoreSpesa == null) {
        break missingId;
      }

      id = R.id.view;
      View view = ViewBindings.findChildViewById(rootView, id);
      if (view == null) {
        break missingId;
      }

      id = R.id.view2;
      View view2 = ViewBindings.findChildViewById(rootView, id);
      if (view2 == null) {
        break missingId;
      }

      id = R.id.view3;
      View view3 = ViewBindings.findChildViewById(rootView, id);
      if (view3 == null) {
        break missingId;
      }

      id = R.id.view4;
      View view4 = ViewBindings.findChildViewById(rootView, id);
      if (view4 == null) {
        break missingId;
      }

      return new ActivityTesseraPuntiBinding((ConstraintLayout) rootView, activityTessPunti,
          codiciSconto, converti, haiSpeso, infoCS, punti, riscatta, rvCodiciSconto, tiPremia,
          valorePunti, valoreSpesa, view, view2, view3, view4);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
