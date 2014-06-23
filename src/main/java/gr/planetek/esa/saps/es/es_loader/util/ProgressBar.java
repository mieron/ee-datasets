package gr.planetek.esa.saps.es.es_loader.util;

import java.text.NumberFormat;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

public class ProgressBar {

    private final String fullBlock = "=";
    private final String nullBlock = "-";
    private int barSize = 60;

    private final int totalSteps;
    private int step = 0;
    private int block = -999;
    private double increment;
    private NumberFormat percentFormat;
    private DateTime startTimer;
    private final PeriodFormatter periodFormatter;

    public ProgressBar(int totalSteps) {
        this.totalSteps = totalSteps;
        increment = barSize * 1.0 / totalSteps;

        percentFormat = NumberFormat.getPercentInstance();
        percentFormat.setMinimumFractionDigits(2);

        periodFormatter = new PeriodFormatterBuilder().appendLiteral(" Done in: ").appendMinutes()
                .appendSuffix(" min", " min").appendSeparator(", ").printZeroAlways()
                .appendSeconds().appendSuffix(" sec", " sec").appendLiteral("             ")
                .toFormatter();
    }

    public void iterate() {
        if (step == 0)
            startTimer = new DateTime();
        step++;
        refresh();
        if (step == totalSteps - 1)
            System.out.println("");
    }

    private void refresh() {
        double progress = step * increment;
        int newBlock = (int) Math.ceil(progress);
        if (block != newBlock) {
            block = newBlock;
            StringBuffer buff = new StringBuffer();
            buff.append("[");
            buff.append(StringUtils.repeat(fullBlock, block));
            buff.append(StringUtils.repeat(nullBlock, barSize - block));
            buff.append("] ");
            buff.append(percentFormat.format(block * 1.0 / barSize));
            Interval elapsed = new Interval(startTimer, new DateTime());
            DateTime endTime = startTimer.plus((long) (elapsed.toDurationMillis()
                    * (totalSteps - step) * 1.0 / step));
            Period period = new Period(startTimer, endTime);
            buff.append(periodFormatter.print(period));

            System.out.print("\r" + buff.toString());

        }
    }

    private void redraw(int newBlock) {
        StringBuffer buf = new StringBuffer();

    }

    public static void main(String[] args) {
        ProgressBar bar = new ProgressBar(117);

        for (int i = 1; i < 117; i++) {
            try {
                Thread.sleep(834);
                bar.iterate();

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
