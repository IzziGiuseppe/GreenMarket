// Generated by view binder compiler. Do not edit!
package com.example.greenmarket.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.greenmarket.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityProfiloUtenteBinding implements ViewBinding {
  @NonNull
  private final ScrollView rootView;

  @NonNull
  public final Button buttonCancel;

  @NonNull
  public final Button buttonSave;

  @NonNull
  public final CardView cardImageProfilo;

  @NonNull
  public final EditText editTextCognomeUP;

  @NonNull
  public final EditText editTextIndirizzoUP;

  @NonNull
  public final EditText editTextNomeUP;

  @NonNull
  public final FloatingActionButton floatingActionButtonModFoto;

  @NonNull
  public final ImageView imageViewProfile;

  @NonNull
  public final LinearLayout linearLayout14;

  @NonNull
  public final LinearLayout linearLayout4;

  @NonNull
  public final TextView textViewEliminaAccount;

  @NonNull
  public final TextView textViewLogout;

  @NonNull
  public final TextView titleCognome;

  @NonNull
  public final TextView titleIndirizzoProfilo;

  @NonNull
  public final TextView titleNome;

  private ActivityProfiloUtenteBinding(@NonNull ScrollView rootView, @NonNull Button buttonCancel,
      @NonNull Button buttonSave, @NonNull CardView cardImageProfilo,
      @NonNull EditText editTextCognomeUP, @NonNull EditText editTextIndirizzoUP,
      @NonNull EditText editTextNomeUP, @NonNull FloatingActionButton floatingActionButtonModFoto,
      @NonNull ImageView imageViewProfile, @NonNull LinearLayout linearLayout14,
      @NonNull LinearLayout linearLayout4, @NonNull TextView textViewEliminaAccount,
      @NonNull TextView textViewLogout, @NonNull TextView titleCognome,
      @NonNull TextView titleIndirizzoProfilo, @NonNull TextView titleNome) {
    this.rootView = rootView;
    this.buttonCancel = buttonCancel;
    this.buttonSave = buttonSave;
    this.cardImageProfilo = cardImageProfilo;
    this.editTextCognomeUP = editTextCognomeUP;
    this.editTextIndirizzoUP = editTextIndirizzoUP;
    this.editTextNomeUP = editTextNomeUP;
    this.floatingActionButtonModFoto = floatingActionButtonModFoto;
    this.imageViewProfile = imageViewProfile;
    this.linearLayout14 = linearLayout14;
    this.linearLayout4 = linearLayout4;
    this.textViewEliminaAccount = textViewEliminaAccount;
    this.textViewLogout = textViewLogout;
    this.titleCognome = titleCognome;
    this.titleIndirizzoProfilo = titleIndirizzoProfilo;
    this.titleNome = titleNome;
  }

  @Override
  @NonNull
  public ScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityProfiloUtenteBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityProfiloUtenteBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_profilo_utente, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityProfiloUtenteBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.buttonCancel;
      Button buttonCancel = ViewBindings.findChildViewById(rootView, id);
      if (buttonCancel == null) {
        break missingId;
      }

      id = R.id.buttonSave;
      Button buttonSave = ViewBindings.findChildViewById(rootView, id);
      if (buttonSave == null) {
        break missingId;
      }

      id = R.id.card_image_profilo;
      CardView cardImageProfilo = ViewBindings.findChildViewById(rootView, id);
      if (cardImageProfilo == null) {
        break missingId;
      }

      id = R.id.editTextCognomeUP;
      EditText editTextCognomeUP = ViewBindings.findChildViewById(rootView, id);
      if (editTextCognomeUP == null) {
        break missingId;
      }

      id = R.id.editTextIndirizzoUP;
      EditText editTextIndirizzoUP = ViewBindings.findChildViewById(rootView, id);
      if (editTextIndirizzoUP == null) {
        break missingId;
      }

      id = R.id.editTextNomeUP;
      EditText editTextNomeUP = ViewBindings.findChildViewById(rootView, id);
      if (editTextNomeUP == null) {
        break missingId;
      }

      id = R.id.floatingActionButtonModFoto;
      FloatingActionButton floatingActionButtonModFoto = ViewBindings.findChildViewById(rootView, id);
      if (floatingActionButtonModFoto == null) {
        break missingId;
      }

      id = R.id.imageViewProfile;
      ImageView imageViewProfile = ViewBindings.findChildViewById(rootView, id);
      if (imageViewProfile == null) {
        break missingId;
      }

      id = R.id.linearLayout14;
      LinearLayout linearLayout14 = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout14 == null) {
        break missingId;
      }

      id = R.id.linearLayout4;
      LinearLayout linearLayout4 = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout4 == null) {
        break missingId;
      }

      id = R.id.textViewEliminaAccount;
      TextView textViewEliminaAccount = ViewBindings.findChildViewById(rootView, id);
      if (textViewEliminaAccount == null) {
        break missingId;
      }

      id = R.id.textViewLogout;
      TextView textViewLogout = ViewBindings.findChildViewById(rootView, id);
      if (textViewLogout == null) {
        break missingId;
      }

      id = R.id.title_cognome;
      TextView titleCognome = ViewBindings.findChildViewById(rootView, id);
      if (titleCognome == null) {
        break missingId;
      }

      id = R.id.title_indirizzo_profilo;
      TextView titleIndirizzoProfilo = ViewBindings.findChildViewById(rootView, id);
      if (titleIndirizzoProfilo == null) {
        break missingId;
      }

      id = R.id.title_nome;
      TextView titleNome = ViewBindings.findChildViewById(rootView, id);
      if (titleNome == null) {
        break missingId;
      }

      return new ActivityProfiloUtenteBinding((ScrollView) rootView, buttonCancel, buttonSave,
          cardImageProfilo, editTextCognomeUP, editTextIndirizzoUP, editTextNomeUP,
          floatingActionButtonModFoto, imageViewProfile, linearLayout14, linearLayout4,
          textViewEliminaAccount, textViewLogout, titleCognome, titleIndirizzoProfilo, titleNome);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
