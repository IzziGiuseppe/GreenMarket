// Generated by view binder compiler. Do not edit!
package com.example.greenmarket.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

public final class ActivityProfiloUtenteBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button buttonCancel;

  @NonNull
  public final Button buttonSave;

  @NonNull
  public final EditText editTextCognomeUP;

  @NonNull
  public final EditText editTextIndirizzoUP;

  @NonNull
  public final EditText editTextNomeUP;

  @NonNull
  public final ImageView imageViewProfile;

  @NonNull
  public final TextView textView17;

  @NonNull
  public final TextView textViewEliminaAccount;

  @NonNull
  public final TextView textViewLogout;

  @NonNull
  public final TextView textViewModProfileImage;

  private ActivityProfiloUtenteBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button buttonCancel, @NonNull Button buttonSave, @NonNull EditText editTextCognomeUP,
      @NonNull EditText editTextIndirizzoUP, @NonNull EditText editTextNomeUP,
      @NonNull ImageView imageViewProfile, @NonNull TextView textView17,
      @NonNull TextView textViewEliminaAccount, @NonNull TextView textViewLogout,
      @NonNull TextView textViewModProfileImage) {
    this.rootView = rootView;
    this.buttonCancel = buttonCancel;
    this.buttonSave = buttonSave;
    this.editTextCognomeUP = editTextCognomeUP;
    this.editTextIndirizzoUP = editTextIndirizzoUP;
    this.editTextNomeUP = editTextNomeUP;
    this.imageViewProfile = imageViewProfile;
    this.textView17 = textView17;
    this.textViewEliminaAccount = textViewEliminaAccount;
    this.textViewLogout = textViewLogout;
    this.textViewModProfileImage = textViewModProfileImage;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
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

      id = R.id.imageViewProfile;
      ImageView imageViewProfile = ViewBindings.findChildViewById(rootView, id);
      if (imageViewProfile == null) {
        break missingId;
      }

      id = R.id.textView17;
      TextView textView17 = ViewBindings.findChildViewById(rootView, id);
      if (textView17 == null) {
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

      id = R.id.textViewModProfileImage;
      TextView textViewModProfileImage = ViewBindings.findChildViewById(rootView, id);
      if (textViewModProfileImage == null) {
        break missingId;
      }

      return new ActivityProfiloUtenteBinding((ConstraintLayout) rootView, buttonCancel, buttonSave,
          editTextCognomeUP, editTextIndirizzoUP, editTextNomeUP, imageViewProfile, textView17,
          textViewEliminaAccount, textViewLogout, textViewModProfileImage);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
