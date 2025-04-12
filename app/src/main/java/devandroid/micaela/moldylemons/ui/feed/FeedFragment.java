package devandroid.micaela.moldylemons.ui.feed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import devandroid.micaela.moldylemons.databinding.FragmentFeedBinding;

public class FeedFragment extends Fragment {

    private FragmentFeedBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FeedViewModel feedViewModel =
                new ViewModelProvider(this).get(FeedViewModel.class);

        this.binding = FragmentFeedBinding.inflate(inflater, container, false);
        View root = this.binding.getRoot();

        final TextView textView = this.binding.textFeed;
        feedViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }
}