import React, {useState} from 'react';
import "./styles/buttons.css";
import button from "./Button";
import Button from "./Button";
import "./styles/text.css";


function TestPage() {
    const [startTime, setStartTime] = useState(null);
    const [endTime, setEndTime] = useState(null);
    const [showResult, setShowResult] = useState(false);
    const [isPlaying, setIsPlaying] = useState(0);
    const [clickCount, setClickCount] = useState(0);
    const [badCount, setBadCount] = useState(0);
    const [wellCount, setWellCount] = useState(0);
    const [allTime, setAllTime] = useState(0);


    const  handleButtonClick = () => {
        setClickCount((count) => count + 1);
    }


    const handleStartTest = async () => {
        setAllTime(0);
        setIsPlaying(1);
        setStartTime(Date.now());
        randomColor();
    }

    const handleStopTest = () => {
        setIsPlaying(2);
        setEndTime(Date.now());
        setShowResult(true);
    };


    const startTimer = () => {
        setStartTime(Date.now())
    }
    const finishTimer = () => {
        setEndTime(Date.now())
    }


    const handleRestartTest = () => {
        setWellCount(0);
        setBadCount(0);
        setStartTime(null);
        setEndTime(null);
        setShowResult(false);
        setIsPlaying(0);
    }


    const result = wellCount ? `Среднее время: ${allTime/wellCount} мс.` : 'Правильных ответов нет.';
    const result1 = showResult && 'Ваш результат: ';
    const divStyle0 = {
        backgroundColor: 'yellow',
        backgroundSize: 'cover',
        borderRadius: '50%',
        borderColor: 'white',
        borderWidth: '3px',
        backgroundPosition: 'center',
        marginLeft: '25px',
        width: '250px',
        height: '250px',
    };
    const divStyle1 = {
        position: 'absolute',
        backgroundColor: 'red',
        backgroundSize: 'cover',
        borderRadius: '50%',
        borderColor: 'white',
        borderWidth: '3px',
        backgroundPosition: 'center',
        width: '250px',
        height: '250px',
        marginLeft: '-520px',
    };
    const divStyle2 = {
        position: 'absolute',
        backgroundColor: 'green',
        backgroundSize: 'cover',
        borderRadius: '50%',
        borderColor: 'white',
        borderWidth: '3px',
        backgroundPosition: 'center',
        width: '250px',
        height: '250px',
        marginLeft: '20px',
    };
    const divStyle3 = {
        position: 'absolute',
        backgroundColor: 'blue',
        backgroundSize: 'cover',
        borderRadius: '50%',
        borderColor: 'white',
        borderWidth: '3px',
        backgroundPosition: 'center',
        width: '250px',
        height: '250px',
        marginLeft: '-790px',
    };
    const divStyle4 = {
        position: 'absolute',
        backgroundColor: 'purple',
        backgroundSize: 'cover',
        borderRadius: '50%',
        borderColor: 'white',
        borderWidth: '3px',
        backgroundPosition: 'center',
        width: '250px',
        height: '250px',
        marginLeft: '290px',
    };

    const phrases = ['ЖЕЛТЫЙ', 'КРАСНЫЙ', 'ЗЕЛЕНЫЙ', 'СИНИЙ', 'ФИОЛЕТОВЫЙ'];
    const [phrase, setPhrase] = useState('');
    function checker(COLOR){
        randomColor();


        startTimer();
        if (COLOR === phrase) {
            setAllTime(allTime + Date.now() - startTime);
            console.log(allTime);
            handleButtonClick();
            setWellCount(wellCount+1);
        }else{
            setBadCount(badCount+1);
        }
        if (wellCount + badCount === 9){
            handleStopTest();
        }
    }

    function colorClick() {
        const randomIndex = Math.floor(Math.random() * phrases.length);
        setPhrase(phrases[randomIndex]);
    }
    function randomColor(){
        //handleStartTest();
        colorClick();
    }
    return (
        <div>
            {isPlaying === 0 ? (
                <Button role={button} onClick={() => {handleStartTest()}} disabled={startTime !== null}>
                    Начать тест
                </Button>
            ) : isPlaying === 1 ? (
                <div>
                    <Button role={button} onClick={() => {handleStopTest()}} disabled={endTime !== null}>
                        Стоп
                    </Button>
                    <div className="lolkek">
                        <p>{phrase}</p>
                    </div>
                        <button onClick={()=>checker('ЖЕЛТЫЙ')} disabled={endTime !== null} style={divStyle0}></button>
                        <button onClick={()=>checker('КРАСНЫЙ')} disabled={endTime !== null} style={divStyle1}></button>
                        <button onClick={()=>checker('ЗЕЛЕНЫЙ')} disabled={endTime !== null} style={divStyle2}></button>
                        <button onClick={()=>checker('СИНИЙ')} disabled={endTime !== null} style={divStyle3}></button>
                        <button onClick={()=>checker('ФИОЛЕТОВЫЙ')} disabled={endTime !== null} style={divStyle4}></button>
                    <div className="showResult1">
                        <br/>
                        <p>Нажато {wellCount + badCount} из 10.</p>
                    </div>
                </div>

            ) : <Button role={button} onClick={handleRestartTest}>
                Перезапустить
            </Button>
            }


            <div className="showResult">
                <style>
                    {`
                .showResult {
                    color: #c7edef;
                    text-align: center;
                }
                `}
                </style>
                <br/>
                {showResult && result}
                <br/>
                {result1}{result1 && wellCount + ' из 10.'}
            </div>
        </div>
    );
}

export default TestPage;