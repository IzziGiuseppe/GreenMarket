// Generated by view binder compiler. Do not edit!
package com.example.greenmarket.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

public final class ActivityDettaglioProdottoBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ConstraintLayout activityDr;

  @NonNull
  public final TextView descrizioneProdotto;

  @NonNull
  public final ImageView fotoProdotto;

  @NonNull
  public final TextView nomeProdotto;

  private ActivityDettaglioProdottoBinding(@NonNull ConstraintLayout rootView,
      @NonNull ConstraintLayout activityDr, @NonNull TextView descrizioneProdotto,
      @NonNull ImageView fotoProdotto, @NonNull TextView nomeProdotto) {
    this.rootView = rootView;
    this.activityDr = activityDr;
    this.descrizioneProdotto = descrizioneProdotto;
    this.fotoProdotto = fotoProdotto;
    this.nomeProdotto = nomeProdotto;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityDettaglioProdottoBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityDettaglioProdottoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_dettaglio_prodotto, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityDettaglioProdottoBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      ConstraintLayout activityDr = (ConstraintLayout) rootView;

      id = R.id.descrizione_prodotto;
      TextView descrizioneProdotto = ViewBindings.findChildViewById(rootView, id);
      if (descrizioneProdotto == null) {
        break missingId;
      }

      id = R.id.foto_prodotto;
      ImageView fotoProdotto = ViewBindings.findChildViewById(rootView, id);
      if (fotoProdotto == null) {
        break missingId;
      }

      id = R.id.nome_prodotto;
      TextView nomeProdotto = ViewBindings.findChildViewById(rootView, id);
      if (nomeProdotto == null) {
        break missingId;
      }

      return new ActivityDettaglioProdottoBinding((ConstraintLayout) rootView, activityDr,
          descrizioneProdotto, fotoProdotto, nomeProdotto);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}