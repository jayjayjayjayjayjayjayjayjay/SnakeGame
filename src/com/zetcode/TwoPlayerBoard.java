package com.zetcode;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

import com.zetcode.Entity.*;
import com.zetcode.Frame.GameFrame;
import com.zetcode.Frame.InGamePanel;

public class TwoPlayerBoard extends JPanel implements ActionListener {

    private final int B_WIDTH = 800;
    private final int B_HEIGHT = 800;
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 900;
    private InGamePanel timePanel;

    //B_WIDTH,B_HEIGHT에 따라 랜덤값 생성
    private final int RAND_POS = (int) Math.ceil((double) Math.min(B_WIDTH, B_HEIGHT - DOT_SIZE) / DOT_SIZE);

    public static int DELAY = 100; ///게임 속도 (지렁이, 메테오 등)

    public static int NUM = 10;  ///메테오, 장애물, 몬스터의 개수

    private SnakeEntity snake;
    private SndSnakeEntity snake2;
    private AppleEntity appleEntity;
    private BoxEntity boxEntity;
    private TrapEntity trapEntity;
    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean leftDirection2 = false;
    private boolean rightDirection2 = true;
    private boolean upDirection2 = false;
    private boolean downDirection2 = false;
    private boolean inGame = true;
    private boolean gamePaused = false;
    private boolean stun = false;
    private boolean stun2 = false;
    public boolean mode2P = true;
    private long stun_start_time;
    private long stun2_start_time;
    private final int max_apple = 5; // 최대 생성 가능한 사과 개수
    private int current_apple = 0; // 현재 생성된 사과 개수
    private final int max_box = 5; // 최대 생성 가능한 랜덤박스 개수
    private int current_box = 0; // 현재 생성된 랜덤박스 개수
    private final int max_trap = 3; // 최대 생성 가능한 트랩 개수
    private int current_trap = 0; // 현재 생성된 트랩 개수
    private final int meteorTime = 1; // 원하는 메테오 시간
    private final int meteorSpeed = 5; // 원하는 메테오 시간

    private long lastMeteorTime;
    private MeteorEntity meteorEntity;

    private ShootEntity shootEntity;
    private final int shootSpeed = 5;

    //지렁이가 무적 상태인지를 저장
    private boolean invincible = false;
    private boolean invincible2 = false;
    //변수와 무적 상태가 시작된 시간을 저장
    private long invincible_start_time;
    private long invincible2_start_time;

    private Image invincible_head;
    private Image invincible_dot;
    private Image invincible_head2;
    private Image invincible_dot2;
    private Image shoot;

    private int score=0;


    private Timer timer;
    private Image ball;
    private Image ball2;
    private Image apple;
    private Image box;
    private Image trap;
    private Image head;
    private Image head2;
    private Image obstacle;
    private Image meteor;
    private Image monster;

    private ObstacleEntity obstacleEntity;
    private MonsterEntity monsterEntity;

    public TwoPlayerBoard() {
        initBoard();
    }

    public static int getDelay() {
        return DELAY;
    }

    public static void setDelay(int delay) {
        DELAY = delay;
    }

    public static int getNum() {
        return NUM;
    }

    public static void setNum(int num) {
        NUM = num;
    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImages();
        initGame();
        timePanel = new InGamePanel();
        timePanel.setBounds(0, 0, 150, 30);
        add(timePanel);
    }

    private void loadImages() {
        ImageIcon iid = new ImageIcon("src/resources/dot.png");
        ball = iid.getImage();

        ImageIcon iid2 = new ImageIcon("src/resources/dot2.png");
        ball2 = iid2.getImage();

        ImageIcon iia = new ImageIcon("src/resources/apple.png");
        apple = iia.getImage();

        ImageIcon iib = new ImageIcon("src/resources/box.png");
        box = iib.getImage();

        ImageIcon iit = new ImageIcon("src/resources/trap.png");
        trap = iit.getImage();

        ImageIcon iih = new ImageIcon("src/resources/head.png");
        head = iih.getImage();

        ImageIcon iih2 = new ImageIcon("src/resources/head2.png");
        head2 = iih2.getImage();

        ImageIcon iio = new ImageIcon("src/resources/obstacle.png");
        obstacle = iio.getImage();

        ImageIcon iim = new ImageIcon(("src/resources/meteor.png"));
        meteor = iim.getImage();

        ImageIcon iimn = new ImageIcon("src/resources/monster.png");
        monster = iimn.getImage();

        ImageIcon iis = new ImageIcon("src/resources/shoot.png");
        shoot = iis.getImage();

        ImageIcon ii_invincible_head = new ImageIcon("src/resources/headshield.png");
        invincible_head = ii_invincible_head.getImage();

        ImageIcon ii_invincible_dot = new ImageIcon("src/resources/dotshield.png");
        invincible_dot = ii_invincible_dot.getImage();

        ImageIcon ii_invincible_head2 = new ImageIcon("src/resources/headshield2.png");
        invincible_head2 = ii_invincible_head2.getImage();

        ImageIcon ii_invincible_dot2 = new ImageIcon("src/resources/dotshield2.png");
        invincible_dot2 = ii_invincible_dot2.getImage();
    }

    private void initGame() {
        snake = new SnakeEntity(ALL_DOTS);
        if (mode2P) {
            snake2 = new SndSnakeEntity(ALL_DOTS);
        }

        appleEntity = new AppleEntity(RAND_POS, DOT_SIZE);
        appleEntity.locateApple(); // Pass RAND_POS to locateApple method

        boxEntity = new BoxEntity(RAND_POS, DOT_SIZE);
        boxEntity.locateBox();

        trapEntity = new TrapEntity(RAND_POS, DOT_SIZE);
        trapEntity.locateTrap();

        //장애물 개수 조절
        obstacleEntity = new ObstacleEntity(NUM, DOT_SIZE, RAND_POS); // Pass RAND_POS to ObstacleEntity constructor

        // 메테오 개수를 원하는 값으로 설정 (예: 10)
        meteorEntity = new MeteorEntity(NUM);
        lastMeteorTime = System.currentTimeMillis();

        //몬수터 수 조절
        monsterEntity = new MonsterEntity(NUM, DOT_SIZE, RAND_POS);

        timer = new Timer(DELAY, this);
        timer.start();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        if (inGame) {
            g.drawImage(apple, appleEntity.getAppleX(), appleEntity.getAppleY(), this);
            g.drawImage(trap, trapEntity.getTrapX(), trapEntity.getTrapY(), this);
            if (snake.getDots() % 4 == 0){ // 지렁이 길이가 4의 배수일 때 랜덤박스 생성
                g.drawImage(box, boxEntity.getBoxX(), boxEntity.getBoxY(), this);
            }
            if (mode2P) {
                if (snake2.getDots() % 4 == 0) { // 지렁이 길이가 4의 배수일 때 랜덤박스 생성
                    g.drawImage(box, boxEntity.getBoxX(), boxEntity.getBoxY(), this);
                }
            }

            for (int z = 0; z < snake.getDots(); z++) {
                if (z == 0) {
                    if (invincible) {
                        g.drawImage(invincible_head, snake.getX()[z], snake.getY()[z], this);
                    } else {
                        g.drawImage(head, snake.getX()[z], snake.getY()[z], this);
                    }
                } else {
                    if (invincible) {
                        g.drawImage(invincible_dot, snake.getX()[z], snake.getY()[z], this);
                    } else {
                        g.drawImage(ball, snake.getX()[z], snake.getY()[z], this);
                    }
                }
            }
            if (mode2P) {
                for (int z = 0; z < snake2.getDots(); z++) {
                    if (z == 0) {
                        if (invincible2) {
                            g.drawImage(invincible_head2, snake2.getX()[z], snake2.getY()[z], this);
                        } else {
                            g.drawImage(head2, snake2.getX()[z], snake2.getY()[z], this);
                        }
                    } else {
                        if (invincible2) {
                            g.drawImage(invincible_dot2, snake2.getX()[z], snake2.getY()[z], this);
                        } else {
                            g.drawImage(ball2, snake2.getX()[z], snake2.getY()[z], this);
                        }
                    }
                }
            }


            for (int i = 0; i < obstacleEntity.getObstacleX().size(); i++) {
                g.drawImage(obstacle, obstacleEntity.getObstacleX().get(i), obstacleEntity.getObstacleY().get(i), this);
            }

            // 지렁이의 길이가 4의 배수일 때 메테오를 그립니다.
            if (snake.getDots() % 4 == 0) {
                for (int i = 0; i < meteorEntity.getMeteorX().length; i++) {
                    g.drawImage(meteorEntity.getMeteorImage(), meteorEntity.getMeteorX()[i], meteorEntity.getMeteorY(), this);
                }
            }
            if (mode2P) {
                if (snake2.getDots() % 4 == 0) {
                    for (int i = 0; i < meteorEntity.getMeteorX().length; i++) {
                        g.drawImage(meteorEntity.getMeteorImage(), meteorEntity.getMeteorX()[i], meteorEntity.getMeteorY(), this);
                    }
                }
            }

            //몬스터
            for (int i = 0; i < monsterEntity.getMonsterX().size(); i++) {
                g.drawImage(monster, monsterEntity.getMonsterX().get(i), monsterEntity.getMonsterY().get(i), this);
            }


            //총알
            for (int i = 0; i < monsterEntity.getShootEntity().getShootX().size(); i++) {
                g.drawImage(shoot, monsterEntity.getShootEntity().getShootX().get(i), monsterEntity.getShootEntity().getShootY().get(i), this);
            }


            Toolkit.getDefaultToolkit().sync();

        } else {
            gameOver(g);
        }
    }


    private void gameOver(Graphics g) {
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
        GameFrame gameFrame = new GameFrame();
        gameFrame.setVisible(true);
    }

    private void checkApple() {
        if ((snake.getX()[0] == appleEntity.getAppleX()) && (snake.getY()[0] == appleEntity.getAppleY())) {
            snake.grow();
            appleEntity.locateApple();
            current_apple++;

            if (current_apple == max_apple) {
                locateApples();
            }

            // 지렁이의 길이가 4의 배수일 때 메테오 생성
            if (snake.getDots() % 4 == 0) {
                meteorEntity = new MeteorEntity(10);
                lastMeteorTime = System.currentTimeMillis();
            }
            timePanel.incrementScore();
        }
        if (mode2P) {
            if ((snake2.getX()[0] == appleEntity.getAppleX()) && (snake2.getY()[0] == appleEntity.getAppleY())) {
                snake2.grow();
                appleEntity.locateApple();
                current_apple++;

                if (current_apple == max_apple) {
                    locateApples();
                }

                // 지렁이의 길이가 4의 배수일 때 메테오 생성
                if (snake2.getDots() % 4 == 0) {
                    meteorEntity = new MeteorEntity(10);
                    lastMeteorTime = System.currentTimeMillis();
                }
                timePanel.incrementScore();
            }
        }
    }

    private void checkBox() {
        if ((snake.getX()[0] == boxEntity.getBoxX()) && (snake.getY()[0] == boxEntity.getBoxY())) {
            //snake.grow();
            boxOpen();
            boxEntity.locateBox();
            current_box++;

            if (current_box == max_box) {
                locateBox();
            }

            // 지렁이의 길이가 4의 배수일 때 메테오 생성
            if (snake.getDots() % 4 == 0) {
                meteorEntity = new MeteorEntity(10);
                lastMeteorTime = System.currentTimeMillis();
            }
        }
        if (mode2P) {
            if ((snake2.getX()[0] == boxEntity.getBoxX()) && (snake2.getY()[0] == boxEntity.getBoxY())) {
                //snake.grow();
                boxOpen2();
                boxEntity.locateBox();
                current_box++;

                if (current_box == max_box) {
                    locateBox();
                }

                // 지렁이의 길이가 4의 배수일 때 메테오 생성
                if (snake2.getDots() % 4 == 0) {
                    meteorEntity = new MeteorEntity(10);
                    lastMeteorTime = System.currentTimeMillis();
                }

            }
        }
    }
    private void checkTrap() {
        if ((snake.getX()[0] == trapEntity.getTrapX()) && (snake.getY()[0] == trapEntity.getTrapY())) {
            snake.shrink();
            stun_start_time = System.currentTimeMillis();
            stun = true;
            trapEntity.locateTrap();
            current_trap++;

            if (current_trap == max_trap) {
                locateTraps();
            }

            // 지렁이의 길이가 4의 배수일 때 메테오 생성
            if (snake.getDots() % 4 == 0) {
                meteorEntity = new MeteorEntity(10);
                lastMeteorTime = System.currentTimeMillis();
            }
            timePanel.decrementScore();
        }
        if (mode2P) {
            if ((snake2.getX()[0] == trapEntity.getTrapX()) && (snake2.getY()[0] == trapEntity.getTrapY())) {
                snake2.shrink();
                stun2_start_time = System.currentTimeMillis();
                stun2 = true;
                trapEntity.locateTrap();
                current_trap++;

                if (current_trap == max_trap) {
                    locateTraps();
                }

                // 지렁이의 길이가 4의 배수일 때 메테오 생성
                if (snake2.getDots() % 4 == 0) {
                    meteorEntity = new MeteorEntity(10);
                    lastMeteorTime = System.currentTimeMillis();
                }
                timePanel.decrementScore();
            }
        }
    }
    private void locateApples() {
        int newApples = (int) (Math.random() * (max_apple + 1));
        for (int i = 0; i < newApples; i++) {
            appleEntity.locateApple();
        }
        current_apple = 0;
    }
    private void locateBox() {
        int newBox = (int) (Math.random() * (max_box + 1));
        for (int i = 0; i < newBox; i++) {
            boxEntity.locateBox();
        }
        current_box = 0;
    }
    private void locateTraps() {
        int newTraps = (int) (Math.random() * (max_trap + 1));
        for (int i = 0; i < newTraps; i++) {
            trapEntity.locateTrap();
        }
        current_trap = 0;
    }
    private void move() {

        if (!stun) {
            snake.move(DOT_SIZE, leftDirection, rightDirection, upDirection, downDirection);
        }
        if (mode2P) {
            if (!stun2) {
                snake2.move(DOT_SIZE, leftDirection2, rightDirection2, upDirection2, downDirection2);
            }
        }
    }

    private void checkCollision() {

        if(!invincible) {
            if (!snake.checkCollision(DOT_SIZE, B_WIDTH, B_HEIGHT)) {
                inGame = false;
            }

            //장애물과 충돌 확인
            for (int i = 0; i < obstacleEntity.getObstacleX().size(); i++) {
                if (snake.getX()[0] == obstacleEntity.getObstacleX().get(i) && snake.getY()[0] == obstacleEntity.getObstacleY().get(i)) {
                    inGame = false;
                }
            }

            // 지렁이의 길이가 4의 배수인 경우에만 메테오와 충돌을 검사합니다.
            if (snake.getDots() % 4 == 0) {
                //메테오와 충돌 확인
                for (int i = 0; i < meteorEntity.getMeteorX().length; i++) {
                    if (Math.abs(snake.getX()[0] - meteorEntity.getMeteorX()[i]) < meteorEntity.getMeteorImage().getWidth(null)
                            && Math.abs(snake.getY()[0] - meteorEntity.getMeteorY()) < meteorEntity.getMeteorImage().getHeight(null)) {
                        inGame = false;
                    }
                }
            }
        }
        if(!invincible2){
            if (mode2P) {
                if (!snake2.checkCollision(DOT_SIZE, B_WIDTH, B_HEIGHT)) {
                    inGame = false;
                }
                // 2P 장애물과 충돌 확인
                for (int i = 0; i < obstacleEntity.getObstacleX().size(); i++) {
                    if (snake2.getX()[0] == obstacleEntity.getObstacleX().get(i) && snake2.getY()[0] == obstacleEntity.getObstacleY().get(i)) {
                        inGame = false;
                    }
                }
                // 2P 지렁이의 길이가 4의 배수인 경우에만 메테오와 충돌을 검사합니다.
                if (snake2.getDots() % 4 == 0) {
                    //메테오와 충돌 확인
                    for (int i = 0; i < meteorEntity.getMeteorX().length; i++) {
                        if (Math.abs(snake2.getX()[0] - meteorEntity.getMeteorX()[i]) < meteorEntity.getMeteorImage().getWidth(null)
                                && Math.abs(snake2.getY()[0] - meteorEntity.getMeteorY()) < meteorEntity.getMeteorImage().getHeight(null)) {
                            inGame = false;
                        }
                    }
                }
            }
        }
        if (snake.getDots() == 0){
            inGame = false;
        }
        if (mode2P) {
            if (snake2.getDots() == 0) {
                inGame = false;
            }
        }
        if (!inGame) {
            timer.stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkApple();
            checkBox();
            checkTrap();
            checkCollision();
            move();
            updateMeteor();
            monsterEntity.updateMonsterAndShootPositions(snake, shootSpeed);
            updateInvincibility();
            updateStun();
        }

        repaint();
    }
    private void updateInvincibility() {
        if (invincible) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - invincible_start_time > 3000) {
                invincible = false;
            }
        }
        if (invincible2){
            long currentTime = System.currentTimeMillis();
            if (currentTime - invincible2_start_time > 3000) {
                invincible2 = false;
            }
        }
    }

    private void updateMeteor() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastMeteorTime > meteorTime) {
            meteorEntity.updatePosition(meteorSpeed); // 원하는 메테오 이동 속도
            lastMeteorTime = currentTime;
        }
    }
    private void updateStun() {
        if (stun) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - stun_start_time > 1000) {
                stun = false;
            }
        }
        if(mode2P) {
            if (stun2) {
                long currentTime = System.currentTimeMillis();
                if (currentTime - stun2_start_time > 1000) {
                    stun2 = false;
                }
            }
        }
    }
    private void boxOpen() {
        int option = (int) ((Math.random() * 5) + 1);
        int option_dots = (int) ((Math.random() * 2) + 1);
        switch(option){
            case 1:
                locateBox();
                if(option_dots == 1) {
                    snake.grow();
                }else {
                    snake.shrink();
                }
                break;
            case 2:
                locateApples();
                if(option_dots == 1) {
                    snake.grow();
                }else {
                    snake.shrink();
                }
                break;
            case 3:
                locateTraps();
                if(option_dots == 1) {
                    snake.grow();
                }else {
                    snake.shrink();
                }
                break;
            case 4:
                incrementScore();
                if(option_dots == 1) {
                    snake.grow();
                }else {
                    snake.shrink();
                }
                break;
            case 5:
                decrementScore();
                if(option_dots == 1) {
                    snake.grow();
                }else {
                    snake.shrink();
                }
                break;
        }
    }
    private void boxOpen2() {
        int option = (int) ((Math.random() * 5) + 1);
        int option_dots = (int) ((Math.random() * 2) + 1);
        switch(option){
            case 1:
                locateBox();
                if(option_dots == 1) {
                    snake2.grow();
                }else {
                    snake2.shrink();
                }
                break;
            case 2:
                locateApples();
                if(option_dots == 1) {
                    snake2.grow();
                }else {
                    snake2.shrink();
                }
                break;
            case 3:
                locateTraps();
                if(option_dots == 1) {
                    snake2.grow();
                }else {
                    snake2.shrink();
                }
                break;
            case 4:
                incrementScore();
                if(option_dots == 1) {
                    snake2.grow();
                }else {
                    snake2.shrink();
                }
                break;
            case 5:
                decrementScore();
                if(option_dots == 1) {
                    snake2.grow();
                }else {
                    snake2.shrink();
                }
                break;
        }
    }


    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
            if (mode2P) {
                if ((key == KeyEvent.VK_A) && (!rightDirection2)) {
                    leftDirection2 = true;
                    upDirection2 = false;
                    downDirection2 = false;
                }

                if ((key == KeyEvent.VK_D) && (!leftDirection2)) {
                    rightDirection2 = true;
                    upDirection2 = false;
                    downDirection2 = false;
                }

                if ((key == KeyEvent.VK_W) && (!downDirection2)) {
                    upDirection2 = true;
                    rightDirection2 = false;
                    leftDirection2 = false;
                }

                if ((key == KeyEvent.VK_S) && (!upDirection2)) {
                    downDirection2 = true;
                    rightDirection2 = false;
                    leftDirection2 = false;
                }
                if (key == KeyEvent.VK_SHIFT) {
                    invincible2 = true;
                    invincible2_start_time = System.currentTimeMillis();
                }
            }
            if (key == KeyEvent.VK_SPACE) {
                invincible = true;
                invincible_start_time = System.currentTimeMillis();
            }
            if(key==KeyEvent.VK_ESCAPE){//게임타이머 정지~
                gamePaused = !gamePaused;
                if (gamePaused) {
                    stopTimer();
                } else {
                    continueTimer();
                }
            }
        }
    }
    public void stopTimer(){
        if(timer!=null){
            timer.stop();
        }
    }
    public void continueTimer(){
        if(timer!=null){
            timer.start();
        }
    }
    private void incrementScore() {
        score++;
        repaint();
    }
    private void decrementScore() {
        score--;
        repaint();
    }
}
