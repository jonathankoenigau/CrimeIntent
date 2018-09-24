package com.example.jonathan.crimeintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

// The fragment that shows the list of crimes
public class CrimeListFragment extends Fragment{
    // The view that creates the list of crimes and allows the list to be scrolled up and down
    private RecyclerView mCrimeRecyclerView;
    // Puts the crimes into each cell of the RecyclerView
    private CrimeAdapter mAdapter;

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Sets the RecyclerView layout
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);

        mCrimeRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    // CrimeLab creates the list of crimes and gives it to the CrimeAdapter
    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();

        if (mAdapter == null) {
            mAdapter = new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mAdapter);
        }
        else {
            mAdapter.notifyDataSetChanged();
        }
    }

    // CrimeHolder is the cell for the RecyclerView
    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Crime in the cell
        private Crime mCrime;
        // Information about the crime in the cell
        private TextView mTitleTextView;
        private TextView mDateTextView;

        public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_crime, parent, false));
            itemView.setOnClickListener(this);

            // Attach variables to parts of the layout
            mTitleTextView = (TextView) itemView.findViewById(R.id.crime_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.crime_date);
        }

        // Update layout using information from crime
        public void bind(Crime crime) {
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());
        }

        @Override
        public void onClick(View view) {
            //Toast.makeText(getActivity(), mCrime.getTitle() + " clicked!", Toast.LENGTH_SHORT).show();
            Intent intent = CrimeActivity.newIntent(getActivity(), mCrime.getID());
            startActivity(intent);
        }
    }

    // Makes CrimeHolder for every crime in the ArrayList
    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {
        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }

        // Creates CrimeHolder cells if they need to be made
        @Override
        public CrimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new CrimeHolder(layoutInflater, parent);
        }

        // When the list is being scrolled through, previous cells will be used to hold new crimes.
        // This method will find what number crime is supposed to be in the cell, then will update the cell with the information from that number crime.
        @Override
        public void onBindViewHolder(@NonNull CrimeHolder holder, int position) {
            Crime crime = mCrimes.get(position);
            holder.bind(crime);
        }

        // Returns size of list
        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }
}
