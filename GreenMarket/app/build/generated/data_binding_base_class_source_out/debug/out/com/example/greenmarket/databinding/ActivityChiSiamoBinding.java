// Generated by view binder compiler. Do not edit!
package com.example.greenmarket.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.greenmarket.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityChiSiamoBinding implements ViewBinding {
  @NonNull
  private final View rootView;

  @NonNull
  public final View activityCs;

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
  public final CardView cardImageDomenico;

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
  public final CardView cardImageGiuseppe;

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
  public final CardView cardImagePeppe;

  /**
   * This binding is not available in all configurations.
   * <p>
   * Present:
   * <ul>
   *   <li>layout/</li>
   * </ul>
   *
   * Absent:
   * <ul>
   *   <li>layout-v26/</li>
   * </ul>
   */
  @Nullable
  public final Button domenicoBt;

  /**
   * This binding is not available in all configurations.
   * <p>
   * Present:
   * <ul>
   *   <li>layout/</li>
   * </ul>
   *
   * Absent:
   * <ul>
   *   <li>layout-v26/</li>
   * </ul>
   */
  @Nullable
  public final Button giuseppeBt;

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
  public final ImageView imageViewDomenico;

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
  public final ImageView imageViewGiuseppe;

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
  public final ImageView imageViewPeppe;

  @NonNull
  public final View mappa;

  @NonNull
  public final TextView testoTxt;

  @NonNull
  public final TextView titolo2Txt;

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
  public final TextView titoloSoci;

  @NonNull
  public final TextView titoloTxt;

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
  public final View view2;

  private ActivityChiSiamoBinding(@NonNull View rootView, @NonNull View activityCs,
      @Nullable CardView cardImageDomenico, @Nullable CardView cardImageGiuseppe,
      @Nullable CardView cardImagePeppe, @Nullable Button domenicoBt, @Nullable Button giuseppeBt,
      @Nullable ImageView imageViewDomenico, @Nullable ImageView imageViewGiuseppe,
      @Nullable ImageView imageViewPeppe, @NonNull View mappa, @NonNull TextView testoTxt,
      @NonNull TextView titolo2Txt, @Nullable TextView titoloSoci, @NonNull TextView titoloTxt,
      @Nullable View view, @Nullable View view2) {
    this.rootView = rootView;
    this.activityCs = activityCs;
    this.cardImageDomenico = cardImageDomenico;
    this.cardImageGiuseppe = cardImageGiuseppe;
    this.cardImagePeppe = cardImagePeppe;
    this.domenicoBt = domenicoBt;
    this.giuseppeBt = giuseppeBt;
    this.imageViewDomenico = imageViewDomenico;
    this.imageViewGiuseppe = imageViewGiuseppe;
    this.imageViewPeppe = imageViewPeppe;
    this.mappa = mappa;
    this.testoTxt = testoTxt;
    this.titolo2Txt = titolo2Txt;
    this.titoloSoci = titoloSoci;
    this.titoloTxt = titoloTxt;
    this.view = view;
    this.view2 = view2;
  }

  @Override
  @NonNull
  public View getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityChiSiamoBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityChiSiamoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_chi_siamo, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityChiSiamoBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      View activityCs = rootView;

      id = R.id.card_image_domenico;
      CardView cardImageDomenico = ViewBindings.findChildViewById(rootView, id);

      id = R.id.card_image_giuseppe;
      CardView cardImageGiuseppe = ViewBindings.findChildViewById(rootView, id);

      id = R.id.card_image_peppe;
      CardView cardImagePeppe = ViewBindings.findChildViewById(rootView, id);

      id = R.id.domenico_bt;
      Button domenicoBt = ViewBindings.findChildViewById(rootView, id);

      id = R.id.giuseppe_bt;
      Button giuseppeBt = ViewBindings.findChildViewById(rootView, id);

      id = R.id.imageViewDomenico;
      ImageView imageViewDomenico = ViewBindings.findChildViewById(rootView, id);

      id = R.id.imageViewGiuseppe;
      ImageView imageViewGiuseppe = ViewBindings.findChildViewById(rootView, id);

      id = R.id.imageViewPeppe;
      ImageView imageViewPeppe = ViewBindings.findChildViewById(rootView, id);

      id = R.id.mappa;
      View mappa = ViewBindings.findChildViewById(rootView, id);
      if (mappa == null) {
        break missingId;
      }

      id = R.id.testo_txt;
      TextView testoTxt = ViewBindings.findChildViewById(rootView, id);
      if (testoTxt == null) {
        break missingId;
      }

      id = R.id.titolo2_txt;
      TextView titolo2Txt = ViewBindings.findChildViewById(rootView, id);
      if (titolo2Txt == null) {
        break missingId;
      }

      id = R.id.titolo_soci;
      TextView titoloSoci = ViewBindings.findChildViewById(rootView, id);

      id = R.id.titolo_txt;
      TextView titoloTxt = ViewBindings.findChildViewById(rootView, id);
      if (titoloTxt == null) {
        break missingId;
      }

      id = R.id.view;
      View view = ViewBindings.findChildViewById(rootView, id);

      id = R.id.view2;
      View view2 = ViewBindings.findChildViewById(rootView, id);

      return new ActivityChiSiamoBinding(rootView, activityCs, cardImageDomenico, cardImageGiuseppe,
          cardImagePeppe, domenicoBt, giuseppeBt, imageViewDomenico, imageViewGiuseppe,
          imageViewPeppe, mappa, testoTxt, titolo2Txt, titoloSoci, titoloTxt, view, view2);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
