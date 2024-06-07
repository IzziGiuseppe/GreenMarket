// Generated by view binder compiler. Do not edit!
package com.example.greenmarket.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.greenmarket.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentListaSpesaBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button confermaOrdine;

  @NonNull
  public final FloatingActionButton deleteAll;

  @NonNull
  public final TextView prezzoTotale;

  @NonNull
  public final RecyclerView rvListaSpesa;

  @NonNull
  public final View view;

  private FragmentListaSpesaBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button confermaOrdine, @NonNull FloatingActionButton deleteAll,
      @NonNull TextView prezzoTotale, @NonNull RecyclerView rvListaSpesa, @NonNull View view) {
    this.rootView = rootView;
    this.confermaOrdine = confermaOrdine;
    this.deleteAll = deleteAll;
    this.prezzoTotale = prezzoTotale;
    this.rvListaSpesa = rvListaSpesa;
    this.view = view;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentListaSpesaBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentListaSpesaBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_lista_spesa, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentListaSpesaBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.conferma_ordine;
      Button confermaOrdine = ViewBindings.findChildViewById(rootView, id);
      if (confermaOrdine == null) {
        break missingId;
      }

      id = R.id.delete_all;
      FloatingActionButton deleteAll = ViewBindings.findChildViewById(rootView, id);
      if (deleteAll == null) {
        break missingId;
      }

      id = R.id.prezzo_totale;
      TextView prezzoTotale = ViewBindings.findChildViewById(rootView, id);
      if (prezzoTotale == null) {
        break missingId;
      }

      id = R.id.rv_lista_spesa;
      RecyclerView rvListaSpesa = ViewBindings.findChildViewById(rootView, id);
      if (rvListaSpesa == null) {
        break missingId;
      }

      id = R.id.view;
      View view = ViewBindings.findChildViewById(rootView, id);
      if (view == null) {
        break missingId;
      }

      return new FragmentListaSpesaBinding((ConstraintLayout) rootView, confermaOrdine, deleteAll,
          prezzoTotale, rvListaSpesa, view);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
