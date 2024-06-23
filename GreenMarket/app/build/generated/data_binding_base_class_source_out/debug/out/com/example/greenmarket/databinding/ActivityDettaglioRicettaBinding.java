// Generated by view binder compiler. Do not edit!
package com.example.greenmarket.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.greenmarket.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityDettaglioRicettaBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ConstraintLayout activityDr;

  /**
   * This binding is not available in all configurations.
   * <p>
   * Present:
   * <ul>
   *   <li>layout-v26/</li>
   * </ul>
   *
   * Absent:
   * <ul>
   *   <li>layout/</li>
   * </ul>
   */
  @Nullable
  public final Button buttonSearchRicetta;

  /**
   * This binding is not available in all configurations.
   * <p>
   * Present:
   * <ul>
   *   <li>layout-v26/</li>
   * </ul>
   *
   * Absent:
   * <ul>
   *   <li>layout/</li>
   * </ul>
   */
  @Nullable
  public final CardView cardImageRicetta;

  @NonNull
  public final TextView descrizioneRicetta;

  /**
   * This binding is not available in all configurations.
   * <p>
   * Present:
   * <ul>
   *   <li>layout-v26/</li>
   * </ul>
   *
   * Absent:
   * <ul>
   *   <li>layout/</li>
   * </ul>
   */
  @Nullable
  public final TextView descrizioneTitle;

  /**
   * This binding is not available in all configurations.
   * <p>
   * Present:
   * <ul>
   *   <li>layout-v26/</li>
   * </ul>
   *
   * Absent:
   * <ul>
   *   <li>layout/</li>
   * </ul>
   */
  @Nullable
  public final ImageView fotoRicetta;

  @NonNull
  public final TextView nomeRicetta;

  /**
   * This binding is not available in all configurations.
   * <p>
   * Present:
   * <ul>
   *   <li>layout-v26/</li>
   * </ul>
   *
   * Absent:
   * <ul>
   *   <li>layout/</li>
   * </ul>
   */
  @Nullable
  public final ScrollView scroolViewDescrizione;

  /**
   * This binding is not available in all configurations.
   * <p>
   * Present:
   * <ul>
   *   <li>layout-v26/</li>
   * </ul>
   *
   * Absent:
   * <ul>
   *   <li>layout/</li>
   * </ul>
   */
  @Nullable
  public final View view;

  private ActivityDettaglioRicettaBinding(@NonNull ConstraintLayout rootView,
      @NonNull ConstraintLayout activityDr, @Nullable Button buttonSearchRicetta,
      @Nullable CardView cardImageRicetta, @NonNull TextView descrizioneRicetta,
      @Nullable TextView descrizioneTitle, @Nullable ImageView fotoRicetta,
      @NonNull TextView nomeRicetta, @Nullable ScrollView scroolViewDescrizione,
      @Nullable View view) {
    this.rootView = rootView;
    this.activityDr = activityDr;
    this.buttonSearchRicetta = buttonSearchRicetta;
    this.cardImageRicetta = cardImageRicetta;
    this.descrizioneRicetta = descrizioneRicetta;
    this.descrizioneTitle = descrizioneTitle;
    this.fotoRicetta = fotoRicetta;
    this.nomeRicetta = nomeRicetta;
    this.scroolViewDescrizione = scroolViewDescrizione;
    this.view = view;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityDettaglioRicettaBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityDettaglioRicettaBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_dettaglio_ricetta, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityDettaglioRicettaBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      ConstraintLayout activityDr = (ConstraintLayout) rootView;

      id = R.id.buttonSearchRicetta;
      Button buttonSearchRicetta = ViewBindings.findChildViewById(rootView, id);

      id = R.id.card_image_ricetta;
      CardView cardImageRicetta = ViewBindings.findChildViewById(rootView, id);

      id = R.id.descrizione_ricetta;
      TextView descrizioneRicetta = ViewBindings.findChildViewById(rootView, id);
      if (descrizioneRicetta == null) {
        break missingId;
      }

      id = R.id.descrizione_title;
      TextView descrizioneTitle = ViewBindings.findChildViewById(rootView, id);

      id = R.id.foto_ricetta;
      ImageView fotoRicetta = ViewBindings.findChildViewById(rootView, id);

      id = R.id.nome_ricetta;
      TextView nomeRicetta = ViewBindings.findChildViewById(rootView, id);
      if (nomeRicetta == null) {
        break missingId;
      }

      id = R.id.scrool_view_descrizione;
      ScrollView scroolViewDescrizione = ViewBindings.findChildViewById(rootView, id);

      id = R.id.view;
      View view = ViewBindings.findChildViewById(rootView, id);

      return new ActivityDettaglioRicettaBinding((ConstraintLayout) rootView, activityDr,
          buttonSearchRicetta, cardImageRicetta, descrizioneRicetta, descrizioneTitle, fotoRicetta,
          nomeRicetta, scroolViewDescrizione, view);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
