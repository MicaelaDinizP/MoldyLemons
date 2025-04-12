package devandroid.micaela.moldylemons.ui.wishlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import devandroid.micaela.moldylemons.databinding.FragmentWishlistBinding;

public class WishlistFragment extends Fragment {

    private FragmentWishlistBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        WishlistViewModel wishlistViewModel =
                new ViewModelProvider(this).get(WishlistViewModel.class);

        this.binding = FragmentWishlistBinding.inflate(inflater, container, false);
        View root = this.binding.getRoot();

        final TextView textView = this.binding.textWishlist;
        wishlistViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }
}