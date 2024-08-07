// Generated by view binder compiler. Do not edit!
package com.example.greenmarket.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.greenmarket.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityRegistrazioneBinding implements ViewBinding {
  @NonNull
  private final ScrollView rootView;

  @NonNull
  public final LinearLayout LinearLayoutRegistrazione;

  @NonNull
  public final Button buttonRegister;

  @NonNull
  public final CheckBox checkBox;

  @NonNull
  public final EditText editTextCognome;

  @NonNull
  public final EditText editTextConfirmPassword;

  @NonNull
  public final EditText editTextEmail;

  @NonNull
  public final EditText editTextIndirizzo;

  @NonNull
  public final EditText editTextNome;

  @NonNull
  public final EditText editTextPassword;

  @NonNull
  public final LinearLayout linearLayout5;

  @NonNull
  public final ProgressBar progressBarRegistrazione;

  @NonNull
  public final TextView textViewAccedi;

  @NonNull
  public final TextView textViewAccount;

  @NonNull
  public final TextView textViewCognome;

  @NonNull
  public final TextView textViewConfirmPassword;

  @NonNull
  public final TextView textViewEmail;

  @NonNull
  public final TextView textViewIndirizzo;

  @NonNull
  public final TextView textViewNome;

  @NonNull
  public final TextView textViewPassword;

  @NonNull
  public final TextView textViewRegister;

  @NonNull
  public final TextView textViewTerminiCondizioni;

  @NonNull
  public final ImageView toggleConfPassVisibilityReg;

  @NonNull
  public final ImageView togglePasswordVisibilityReg;

  private ActivityRegistrazioneBinding(@NonNull ScrollView rootView,
      @NonNull LinearLayout LinearLayoutRegistrazione, @NonNull Button buttonRegister,
      @NonNull CheckBox checkBox, @NonNull EditText editTextCognome,
      @NonNull EditText editTextConfirmPassword, @NonNull EditText editTextEmail,
      @NonNull EditText editTextIndirizzo, @NonNull EditText editTextNome,
      @NonNull EditText editTextPassword, @NonNull LinearLayout linearLayout5,
      @NonNull ProgressBar progressBarRegistrazione, @NonNull TextView textViewAccedi,
      @NonNull TextView textViewAccount, @NonNull TextView textViewCognome,
      @NonNull TextView textViewConfirmPassword, @NonNull TextView textViewEmail,
      @NonNull TextView textViewIndirizzo, @NonNull TextView textViewNome,
      @NonNull TextView textViewPassword, @NonNull TextView textViewRegister,
      @NonNull TextView textViewTerminiCondizioni, @NonNull ImageView toggleConfPassVisibilityReg,
      @NonNull ImageView togglePasswordVisibilityReg) {
    this.rootView = rootView;
    this.LinearLayoutRegistrazione = LinearLayoutRegistrazione;
    this.buttonRegister = buttonRegister;
    this.checkBox = checkBox;
    this.editTextCognome = editTextCognome;
    this.editTextConfirmPassword = editTextConfirmPassword;
    this.editTextEmail = editTextEmail;
    this.editTextIndirizzo = editTextIndirizzo;
    this.editTextNome = editTextNome;
    this.editTextPassword = editTextPassword;
    this.linearLayout5 = linearLayout5;
    this.progressBarRegistrazione = progressBarRegistrazione;
    this.textViewAccedi = textViewAccedi;
    this.textViewAccount = textViewAccount;
    this.textViewCognome = textViewCognome;
    this.textViewConfirmPassword = textViewConfirmPassword;
    this.textViewEmail = textViewEmail;
    this.textViewIndirizzo = textViewIndirizzo;
    this.textViewNome = textViewNome;
    this.textViewPassword = textViewPassword;
    this.textViewRegister = textViewRegister;
    this.textViewTerminiCondizioni = textViewTerminiCondizioni;
    this.toggleConfPassVisibilityReg = toggleConfPassVisibilityReg;
    this.togglePasswordVisibilityReg = togglePasswordVisibilityReg;
  }

  @Override
  @NonNull
  public ScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityRegistrazioneBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityRegistrazioneBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_registrazione, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityRegistrazioneBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.LinearLayoutRegistrazione;
      LinearLayout LinearLayoutRegistrazione = ViewBindings.findChildViewById(rootView, id);
      if (LinearLayoutRegistrazione == null) {
        break missingId;
      }

      id = R.id.buttonRegister;
      Button buttonRegister = ViewBindings.findChildViewById(rootView, id);
      if (buttonRegister == null) {
        break missingId;
      }

      id = R.id.checkBox;
      CheckBox checkBox = ViewBindings.findChildViewById(rootView, id);
      if (checkBox == null) {
        break missingId;
      }

      id = R.id.editTextCognome;
      EditText editTextCognome = ViewBindings.findChildViewById(rootView, id);
      if (editTextCognome == null) {
        break missingId;
      }

      id = R.id.editTextConfirmPassword;
      EditText editTextConfirmPassword = ViewBindings.findChildViewById(rootView, id);
      if (editTextConfirmPassword == null) {
        break missingId;
      }

      id = R.id.editTextEmail;
      EditText editTextEmail = ViewBindings.findChildViewById(rootView, id);
      if (editTextEmail == null) {
        break missingId;
      }

      id = R.id.editTextIndirizzo;
      EditText editTextIndirizzo = ViewBindings.findChildViewById(rootView, id);
      if (editTextIndirizzo == null) {
        break missingId;
      }

      id = R.id.editTextNome;
      EditText editTextNome = ViewBindings.findChildViewById(rootView, id);
      if (editTextNome == null) {
        break missingId;
      }

      id = R.id.editTextPassword;
      EditText editTextPassword = ViewBindings.findChildViewById(rootView, id);
      if (editTextPassword == null) {
        break missingId;
      }

      id = R.id.linearLayout5;
      LinearLayout linearLayout5 = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout5 == null) {
        break missingId;
      }

      id = R.id.progressBar_registrazione;
      ProgressBar progressBarRegistrazione = ViewBindings.findChildViewById(rootView, id);
      if (progressBarRegistrazione == null) {
        break missingId;
      }

      id = R.id.textViewAccedi;
      TextView textViewAccedi = ViewBindings.findChildViewById(rootView, id);
      if (textViewAccedi == null) {
        break missingId;
      }

      id = R.id.textViewAccount;
      TextView textViewAccount = ViewBindings.findChildViewById(rootView, id);
      if (textViewAccount == null) {
        break missingId;
      }

      id = R.id.textViewCognome;
      TextView textViewCognome = ViewBindings.findChildViewById(rootView, id);
      if (textViewCognome == null) {
        break missingId;
      }

      id = R.id.textViewConfirmPassword;
      TextView textViewConfirmPassword = ViewBindings.findChildViewById(rootView, id);
      if (textViewConfirmPassword == null) {
        break missingId;
      }

      id = R.id.textViewEmail;
      TextView textViewEmail = ViewBindings.findChildViewById(rootView, id);
      if (textViewEmail == null) {
        break missingId;
      }

      id = R.id.textViewIndirizzo;
      TextView textViewIndirizzo = ViewBindings.findChildViewById(rootView, id);
      if (textViewIndirizzo == null) {
        break missingId;
      }

      id = R.id.textViewNome;
      TextView textViewNome = ViewBindings.findChildViewById(rootView, id);
      if (textViewNome == null) {
        break missingId;
      }

      id = R.id.textViewPassword;
      TextView textViewPassword = ViewBindings.findChildViewById(rootView, id);
      if (textViewPassword == null) {
        break missingId;
      }

      id = R.id.textViewRegister;
      TextView textViewRegister = ViewBindings.findChildViewById(rootView, id);
      if (textViewRegister == null) {
        break missingId;
      }

      id = R.id.textViewTerminiCondizioni;
      TextView textViewTerminiCondizioni = ViewBindings.findChildViewById(rootView, id);
      if (textViewTerminiCondizioni == null) {
        break missingId;
      }

      id = R.id.toggleConfPassVisibilityReg;
      ImageView toggleConfPassVisibilityReg = ViewBindings.findChildViewById(rootView, id);
      if (toggleConfPassVisibilityReg == null) {
        break missingId;
      }

      id = R.id.togglePasswordVisibilityReg;
      ImageView togglePasswordVisibilityReg = ViewBindings.findChildViewById(rootView, id);
      if (togglePasswordVisibilityReg == null) {
        break missingId;
      }

      return new ActivityRegistrazioneBinding((ScrollView) rootView, LinearLayoutRegistrazione,
          buttonRegister, checkBox, editTextCognome, editTextConfirmPassword, editTextEmail,
          editTextIndirizzo, editTextNome, editTextPassword, linearLayout5,
          progressBarRegistrazione, textViewAccedi, textViewAccount, textViewCognome,
          textViewConfirmPassword, textViewEmail, textViewIndirizzo, textViewNome, textViewPassword,
          textViewRegister, textViewTerminiCondizioni, toggleConfPassVisibilityReg,
          togglePasswordVisibilityReg);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
