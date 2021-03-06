package com.prasilabs.droidwizard.modules.simpleFragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.prasilabs.droidwizard.R;
import com.prasilabs.droidwizard.modules.simpleActivity.QuestionsAdapter;
import com.prasilabs.droidwizard.pojos.QuestionsPojo;
import com.prasilabs.droidwizardlib.core.CoreCallBack;
import com.prasilabs.droidwizardlib.core.CoreFragment;

import java.util.List;

/**
 * Created by prasi on 9/5/17.
 */

public class SimpleFragment extends CoreFragment<SimpleFragmentPresenter> implements SimpleFragmentCallBack
{
    private QuestionsAdapter questionsAdapter = new QuestionsAdapter();

    private RecyclerView recyclerView;
    private LinearLayout emptyLayout;

    @Override
    protected SimpleFragmentPresenter setCorePresenter() {
        return new SimpleFragmentPresenter();
    }

    @Override
    protected void initializeView(Bundle savedInstanceState)
    {
        emptyLayout = (LinearLayout) getFragmentView().findViewById(R.id.empty_layout);
        recyclerView = (RecyclerView) getFragmentView().findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(questionsAdapter);

        getPresenter().getQuestions();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_simple;
    }

    @Override
    protected CoreCallBack getCoreCallBack() {
        return this;
    }

    @Override
    public void showQuestions(List<QuestionsPojo> questions)
    {
        questionsAdapter.clearAndAddItem(questions);
        emptyLayout.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmpty()
    {

        emptyLayout.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }
}
