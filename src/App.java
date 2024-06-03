import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import java.awt.RenderingHints;
import java.awt.Stroke;

public class App {
    public static int TailleX = 1600-100;
    public static int TailleY = 800-100;
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        Champ2V2 vélocité = new Champ2V2(TailleX, TailleY, 256, 128);
        //vélocité.remplir(new Vecteur2D(10.0,0.0));
        Vecteur2D dir = new Vecteur2D(0,3.0); //.rot(Math.PI/8.0);
        Champ2V1 fumée = new Champ2V1(TailleX, TailleY,256,128);
        Champ2V1 pression = new Champ2V1(TailleX,TailleY,256,128);
        // pression.remplir(1.0);
        //for (int x = -0; x <= 0; x++) {
        //    for (int y = -0; y <= 0; y++) {
        //        fumée.c[fumée.nX/2 + x][fumée.nY/2 + y] = 10.0;
        //    }
        //}

        Champ2V1 obstacle = new Champ2V1(TailleX, TailleY,256,128);
        obstacle.remplir(1.0);
        for (int x = -20; x <= 20; x++) {
            for (int y = -20; y <= 20; y++) {
                if(x*x + y*y <= 10*10){
                    obstacle.c[14  + x][obstacle.nY/2 + y] = 0.0;
                }
            }
        }

        for (int x = 0; x < vélocité.nX; x++) {
            for (int y = 0; y < vélocité.nY; y++) {
                //vélocité.c[x][y] = new Vecteur2D(x,y).norm();
            }
        }

        //vélocité.c[vélocité.nX/2][vélocité.nY/2] = dir.copier();

        BoucleDessin bd = new BoucleDessin();
        bd.miseÀJour(vélocité, fumée);
        Thread thread = new Thread(bd);
        thread.start();

        while (true) {
            //Thread.sleep(1000);
            //for (int x = 0; x < vélocité.nX; x++) {
            //    for (int y = 0; y < vélocité.nY; y++) {
            //        if( x == 0 || y == 0 || y == vélocité.nY-1 || x == vélocité.nX-1){
            //            vélocité.c[x][y] = new Vecteur2D(10.0,0.0); //Math.random()*10.0,0.0);
            //            if((double)y-(5.0*(double)Math.floor(y/5)) < 0.1){
            //                fumée.c[x][y] = 5.0; //Math.random()*5.0;
            //            }
            //        }
            //    }
            //}
            for (int N2 = 0; N2 < 50; N2++) {
                for (int x = 0; x < vélocité.nX; x++) {
                    for (int y = 0; y < vélocité.nY; y++) {
                        if( x == 0 || y == 0 || y == vélocité.nY-1 || x == vélocité.nX-1){
                            vélocité.c[x][y] = new Vecteur2D(10.0,0.0); //Math.random()*10.0,0.0);
                            if((double)y-(10.0*(double)Math.floor(y/10)) < 0.1){
                                fumée.c[x][y] = 5.0; //Math.random()*5.0;
                            }
                        }
                    }
                }
                vélocité.retirerDiv(1, obstacle);
                //for (int x = 0; x < vélocité.nX; x++) {
                //    for (int y = 0; y < vélocité.nY; y++) {
                //        pression.c[x][y] = 10.0;
                //        bd.miseÀJour(vélocité, pression.copier().mult(100.0));
                //        Thread.sleep(1000);
                //        double va = 0.0;
                //        double vb = 0.0;
                //        double vc = 0.0;
                //        double vd = 0.0;
                //        double ve = 0.0;
                //        double vf = 0.0;
                //        double vg = 0.0;
                //        double vh = 0.0;
                //        double n = 0.0;
                //        if(x != 0 && (obstacle == null || obstacle.c[x-1][y] > 0.001)){
                //            va = V2.scal(vélocité.c[x-1][y], new V2(-1.0,0.0));
                //            n++;
                //        }else{
                //            System.currentTimeMillis();
                //        }
                //        if(x != vélocité.nX-1 && (obstacle == null || obstacle.c[x+1][y] > 0.001)){
                //            vb = V2.scal(vélocité.c[x+1][y], new V2(1.0,0.0));
                //            n++;
                //        }
                //        if(y != 0 && (obstacle == null || obstacle.c[x][y-1] > 0.001)){
                //            vc = V2.scal(vélocité.c[x][y-1], new V2(0.0,-1.0));
                //            n++;
                //        }
                //        if(y != vélocité.nY-1 && (obstacle == null || obstacle.c[x][y+1] > 0.001)){
                //            vd = V2.scal(vélocité.c[x][y+1], new V2(0.0,1.0));
                //            n++;
                //        }
                //        double D = (va+vb+vc+vd+ve+vf+vg+vh)/(double)n;
                //        if(x != 0 && (obstacle == null || obstacle.c[x-1][y] > 0.001)){
                //            vélocité.c[x-1][y].addi(new Vecteur2D(D,0.0));
                //        }
                //        if(x != vélocité.nX-1 && (obstacle == null || obstacle.c[x+1][y] > 0.001)){
                //            vélocité.c[x+1][y].addi(new Vecteur2D(-D,0.0));
                //        }
                //        if(y != 0 && (obstacle == null || obstacle.c[x][y-1] > 0.001)){
                //            vélocité.c[x][y-1].addi(new Vecteur2D(0.0,D));
                //        }
                //        if(y != vélocité.nY-1 && (obstacle == null || obstacle.c[x][y+1] > 0.001)){
                //            vélocité.c[x][y+1].addi(new Vecteur2D(0.0,-D));
                //        }
                //        if(obstacle != null && obstacle.c[x][y] < 0.001){
                //            vélocité.c[x][y] = new Vecteur2D(0.0);
                //        }
                //        pression =  vélocité.Div(obstacle).mult(-1.0);
                //        bd.miseÀJour(vélocité, pression.copier().mult(100.0));
                //        //Thread.sleep((long)(Math.abs(D)*300.0));
                //    }
                //}
                pression =  vélocité.Div(obstacle).mult(-1.0);
                //vélocité.addi(pression.grad(obstacle).mult(new Vecteur2D(-1.0)));
                //vélocité.addi( pression.grad(null).mult(new Vecteur2D(-1.0)));
                //bd.miseÀJour(pression.grad(null).mult(new Vecteur2D(100000.0)), pression.copier().mult(100.0));
            }

            for (int N2 = 0; N2 < 10; N2++) {
                Champ2V2 tmp = new Champ2V2(vélocité);
                Champ2V1 tmp2 = new Champ2V1(fumée);
                Champ2V1 tmp3 = new Champ2V1(pression);
                //tmp.remplir(new Vecteur2D(0));
                //tmp2.remplir(0);
                for (int x = 0; x < vélocité.nX; x++) {
                    for (int y = 0; y < vélocité.nY; y++) {
                        Vecteur2D vel = vélocité.c((double)x/(double)(vélocité.nX-1), (double)y/(double)(vélocité.nY-1)).mult(0.2/10.0);
                        double éX = ((double)x/(double)(vélocité.nX-1))+((double)vel.x/vélocité.L);
                        double éY = ((double)y/(double)(vélocité.nY-1))+((double)vel.y/vélocité.H);
                        //if(éX >= 0.0 && éX <= 1.0 && éY >= 0.0 && éY <= 1.0){
                        //    tmp.c[x][y] = vélocité.c(éX,éY);
                        //}else{
                        //    tmp.c[x][y] = new Vecteur2D(0.0);
                        //}
                        tmp.cA(éX,éY, vélocité.c[x][y]);
                        tmp.c[x][y].sous( vélocité.c[x][y] );
                        //if(éX >= 0.0 && éX <= 1.0 && éY >= 0.0 && éY <= 1.0){
                        //    tmp2.c[x][y] = fumée.c(éX,éY);
                        //}else{
                        //    tmp2.c[x][y] = 0.0;
                        //}
                        tmp2.cA(éX,éY, fumée.c[x][y]);
                        tmp2.c[x][y] -= fumée.c[x][y];
                        
                        if(Double.isNaN(tmp3.c[x][y]) || Double.isInfinite(tmp3.c[x][y])){
                            tmp3.c[x][y] = 1.0;
                        }
                        tmp3.cA(éX,éY, pression.c[x][y]);
                        tmp3.c[x][y] -= pression.c[x][y];
                        if(obstacle.c[x][y] < 0.001){
                            tmp.c[x][y] = new Vecteur2D(0.0);
                            tmp2.c[x][y] = 0.0;
                            tmp3.c[x][y] = 0.0;
                        }
                    }
                }
                vélocité.copier(tmp);
                fumée.copier(tmp2);
                pression.copier(tmp3);
            }
            //fumée.diffuser(3, 0.01);
            //vélocité.diffuser(3, 0.01);
            bd.miseÀJour(vélocité, fumée);
            if(!thread.isAlive()){
                thread = new Thread(bd);
                thread.start();
            }
        }
    }

    public static class BoucleDessin implements Runnable{

        private Graphics2D g;
        private JFrame frame;

        private Champ2V1 fumée;
        private Champ2V2 vélocité;

        public BoucleDessin(){
            BufferedImage b = new BufferedImage(TailleX, TailleY, BufferedImage.TYPE_4BYTE_ABGR);
            g = (Graphics2D) b.getGraphics();

            frame = new JFrame();
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setSize(TailleX + 100,TailleY + 100);
            frame.setVisible(true);
            JLabel image = new JLabel( new ImageIcon(b));
            frame.add(image);

            g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
            g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

            g.setStroke(new BasicStroke());
            g.setColor(Color.red);
            g.fillRect(0, 0, 100, 100);
        }

        public void miseÀJour(Champ2V2 vélocité, Champ2V1 fumée){
            this.vélocité = vélocité.copier();
            this.fumée = fumée.copier();
        }

        @Override
        public void run() {
            while(true){
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, TailleX, TailleY);
                if(fumée == null || vélocité == null){
                    continue;
                }
                dessinerChamp(fumée, 1, g, frame);
                dessinerChamp(vélocité, 7, g, frame);
                SwingUtilities.updateComponentTreeUI(frame);
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        
    }

    public static void dessinerChamp(Champ2V2 champ, int saut, Graphics2D g, JFrame frame){
        double cL = (double)TailleX/(double)champ.nX;
        double cH = (double)TailleY/(double)champ.nY;
        //for (int x = 0; x < champ.nX; x++) {
        //    for (int y = 0; y < champ.nY; y++) {
        //        g.setColor(new Color((int)((1.0-1.0/(Math.abs(champ.c[x][y].x)+1.0))*255.0),(int)((1.0-1.0/(Math.abs(champ.c[x][y].y)+1.0))*255.0),0));
        //        g.setColor(Color.BLACK);
        //        g.fillRect((int)((double)x*cL), TailleY - (int)((double)(y+1)*cH), (int)Math.ceil(cL), (int)Math.ceil(cH));
        //        //SwingUtilities.updateComponentTreeUI(frame);
        //    }
        //}
        cL = (double)saut*(double)TailleX/(double)champ.nX;
        cH = (double)saut*(double)TailleY/(double)champ.nY;
        g.setColor(Color.GREEN);
        for (int x = 0; x < champ.nX/saut; x++) {
            for (int y = 0; y < champ.nY/saut; y++) {
                Vecteur2D V = champ.c((double)(x*saut)/(double)(champ.nX-1),(double)(y*saut)/(double)(champ.nY-1)).copier();
                double L = V.longueur();
                V.norm();
                int lX = (int)((1.0-1.0/(L+1.0))*Math.min(cL, cH)*V.x*0.5);
                int lY = (int)((1.0-1.0/(L+1.0))*Math.min(cL, cH)*V.y*0.5);
                int a = (int)((double)x*cL) + (int)(cL/2.0);
                int b = (int)((double)TailleY - (double)(y+1)*cH) + (int)(cH/2.0);
                int c = (int)((double)x*cL + (cL/2) + lX);
                int d = (int)((double)TailleY - (double)(y+1)*cH + (cH/2.0) - lY);
                g.drawLine(a, b, c, d);
                //SwingUtilities.updateComponentTreeUI(frame);
            }
        }
        System.currentTimeMillis();
    }

    public static void dessinerChamp(Champ2V1 champ, int saut, Graphics2D g, JFrame frame){
        double cL = (double)TailleX/(double)champ.nX;
        double cH = (double)TailleY/(double)champ.nY;
        for (int x = 0; x < champ.c.length; x++) {
            for (int y = 0; y < champ.c[x].length; y++) {
                int col = (int)((1.0-1.0/(Math.abs(champ.c[x][y])+1.0))*255.0);
                g.setColor(new Color(Math.max((int)Math.signum(champ.c[x][y]),0)*col,0,Math.max(-(int)Math.signum(champ.c[x][y]),0)*col));
                g.fillRect((int)((double)x*cL), TailleY - (int)((double)(y+1)*cH), (int)Math.ceil(cL), (int)Math.ceil(cH));
                //SwingUtilities.updateComponentTreeUI(frame);
            }
        }
    }
}
