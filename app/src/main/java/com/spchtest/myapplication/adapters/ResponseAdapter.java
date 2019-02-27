package com.spchtest.myapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.spchtest.myapplication.R;
import com.spchtest.myapplication.models.CallData;
import com.spchtest.myapplication.models.Record;
import com.spchtest.myapplication.models.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ResponseAdapter extends RecyclerView.Adapter<ResponseAdapter.ResponseViewHolder> {

    CallData callData;
    private List<Record> lstRecord = new ArrayList<>();
    /*private List<String> lstYears = new ArrayList<>();
    HashMap<String,Double> quarterPerformance = new HashMap<>();
    HashMap<String,Double> yearPerformance = new HashMap<>();
    HashMap<String,List<String>> availableQuartersinYear = new HashMap<>();*/


    List<String> lstQuartersForCurrentYear = new ArrayList<>();
    List<String> lstYears = new ArrayList<>();
    List<String> lstQuarters = new ArrayList<>();
    HashMap<String,Double> quarterPerformance = new HashMap<>();
    HashMap<String,Double> yearPerformance = new HashMap<>();
    HashMap<String,List<String>> availableQuartersinYear = new HashMap<>();
    String previousQtr="",currentQtr="",prevYear="";

    public interface OnItemClickListener{
        void onCompareClick(int position);
    }

    public ResponseAdapter(){}

  /*  public ResponseAdapter(List<String> lstYears, HashMap<String,Double> quarterPerformance,HashMap<String,Double> yearPerformance,HashMap<String,List<String>> availableQuartersinYear){
        this.lstYears = lstYears;
        this.quarterPerformance = quarterPerformance;
        this.yearPerformance = yearPerformance;
    }*/

    @NonNull
    @Override
    public ResponseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.response_item,viewGroup,false);
        return new ResponseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ResponseViewHolder responseViewHolder, int i) {
        try
        {
            double currentQTRPerf = 0.0,previousQTRPerf=0.0;
            String currentYear = lstYears.get(i);
            double yearPerf = yearPerformance.get(currentYear);
            responseViewHolder.txtYear.setText("Year : "+currentYear);
            responseViewHolder.txtGrowth.setText("Year Volume : "+yearPerf);
            List<String> lstAvailableQtrs = availableQuartersinYear.get(currentYear);
           // for(int j=0;j<lstQuarters.size();j++){
            if(prevYear == ""){
                prevYear = currentYear;
            }
            if(prevYear == currentYear){
                currentQtr = lstQuarters.get(i);
                if(previousQtr == ""){
                    previousQtr = currentQtr;
                }
                if(previousQtr == currentQtr){
                    currentQTRPerf = quarterPerformance.get(currentQtr);
                }else{
                    previousQTRPerf = quarterPerformance.get(previousQtr);
                }
                if(currentQTRPerf<previousQTRPerf){
                    responseViewHolder.btnChange.setVisibility(View.VISIBLE);
                }else{
                    responseViewHolder.btnChange.setVisibility(View.INVISIBLE);
                }
            }else{
                prevYear = currentYear;
            }

          //  }


        }
        catch (Exception e){

        }

      //  String  avaialbeQuarter = availableQuartersinYear.get(currentYear).get(0);

       // responseViewHolder.txtGrowth.setText(callData.getId());
    }

    public void setCallData(CallData lstCallData){
        this.callData = lstCallData;
        prepareList(lstCallData);
        lstRecord = callData.getResult().getRecords();
        notifyDataSetChanged();
    }

    public void prepareList(CallData cData){
        List<Record> lstRecord = cData.getResult().getRecords();
        String currentYear = "";
        String previousYear = "";

        double currentYearPerformance = 0.0;

        for(int i=0;i<lstRecord.size();i++){
            String quarter = lstRecord.get(i).getQuarter();
            List<String> availableQuartersInCurrentYear = new ArrayList<>();
            double volumeForCurrentQuarter = Double.parseDouble(lstRecord.get(i).getVolumeOfMobileData());
            String[] quarterArray = quarter.split("-");
            currentYear = quarterArray[0];
            if(previousYear == ""){
                previousYear = currentYear;
                lstYears.add(currentYear);
            }
            if(previousYear.equals(currentYear)){
                lstQuartersForCurrentYear.add(quarterArray[1]);
                lstQuarters.add(quarter);
                quarterPerformance.put(quarter,volumeForCurrentQuarter);
                currentYearPerformance += volumeForCurrentQuarter;
                availableQuartersInCurrentYear.add(quarterArray[1]);
            }else{
                lstYears.add(currentYear);

                availableQuartersinYear.put(previousYear,availableQuartersInCurrentYear);
                availableQuartersInCurrentYear = new ArrayList<>();
                availableQuartersInCurrentYear.add(quarterArray[1]);

                lstQuartersForCurrentYear.add(currentYear);
                quarterPerformance.put(quarter,volumeForCurrentQuarter);
                lstQuarters.add(quarter);
            }

            yearPerformance.put(previousYear,currentYearPerformance);
            previousYear = currentYear;
            currentYearPerformance = 0.0;
            currentYearPerformance += volumeForCurrentQuarter;

        }
    }

    @Override
    public int getItemCount() {
        return lstYears.size();
    }

    class ResponseViewHolder extends RecyclerView.ViewHolder{

        private TextView txtYear,txtGrowth;
        private ImageView btnChange;

        public ResponseViewHolder(View itemView){
            super(itemView);
            txtYear = itemView.findViewById(R.id.txtYear);
            txtGrowth = itemView.findViewById(R.id.txtGrowthPerYear);
            btnChange = itemView.findViewById(R.id.btnDecrease);

          /*  btnChange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION)
                    }
                }
            });*/
        }
    }
}
