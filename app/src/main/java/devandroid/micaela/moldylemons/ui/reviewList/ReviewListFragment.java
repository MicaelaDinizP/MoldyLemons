package devandroid.micaela.moldylemons.ui.reviewList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import devandroid.micaela.moldylemons.databinding.FragmentReviewListBinding;

public class ReviewListFragment extends Fragment {

    private FragmentReviewListBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ReviewListViewModel reviewListViewModel =
                new ViewModelProvider(this).get(ReviewListViewModel.class);

        this.binding = FragmentReviewListBinding.inflate(inflater, container, false);
        View root = this.binding.getRoot();

        final TextView textView = this.binding.textReviewList;
        reviewListViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }
}