<?php

require_once dirname(__FILE__) . "/config.php";

if ($_SERVER['REQUEST_METHOD'] === 'POST' && !empty($_POST['amount'])) {
    $value = floatval($_POST['amount']);
    $connection = fsockopen(SERVER, PORT);
    fwrite($connection, sprintf("%.2f\n", $value));
    fflush($connection);

    $answer = "";
    while (!feof($connection)) {
        $answer .= fread($connection, 1024);
    }
    fclose($connection);
}
else {
    $answer = null;
}

?>

    <!-- Der neue HTML Doctype ist der einfachste Doctype den es je gab.-->
    <!DOCTYPE html>
    <html lang="de">
    <head>
        <!-- Auch das meta-Tag zum Zeichensatz wurde vereinfacht.-->
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">

        <style>
            html {
                background-color: aliceblue;
            }

            * {
                color: #1c1c1c;
                font-size: 20px;
                font-weight: 400;
                box-sizing: border-box;
                font-family: 'Monteserat', Arial, sans-serif;
            }

            main {
                padding: 20px;
                background-color: white;
                margin: 20px auto;
                border-radius: 20px;
                max-width: 400px;
            }

            h1 {
                font-size: 36px;
                font-weight: bold;
                margin-top: 0;
            }

            .text-muted {
                font-size: 16px;
                color: #303030;
                margin-bottom: 24px;
            }

            .text-muted b {
                font-size: 16px;
                font-weight: bold;
            }

            label, input {
                display: block;
            }

            label {
                font-weight: bold;
                margin-bottom: 4px;
            }

            input[type="number"] {
                width: 100%;
                border: 1px solid #B0B0B0;
                padding: 10px 15px;
                border-radius: 5px;
                font-size: 20px;
                font-weight: bold;
                margin-bottom: 20px;
            }

            input[type="submit"] {
                background-color: #245682;
                color: #fff;
                border: 1px solid #245682;
                border-radius: 0.25rem;
                display: inline-block;
                font-size: 1rem;
                font-weight: 400;
                line-height: 1.5;
                padding: 0.375rem 0.75rem;
                text-align: center;
                vertical-align: middle;
            }

        </style>
        <title>BHT Bank 1127</title>
    </head>
    <body>
        <main>
            <h1>BHT Bank 1127</h1>
            <p class="text-muted">
                Willkommen bei der kleinen BHT 1127-Übungsbank. Sie können auf das Konto Geld einzahlen oder abheben
                indem Sie in das Eingabefeld eine entsprechende Zahl eingeben. Verwenden Sie ein positive Zahl für Einzahlungen
                und eine negative Zahl für Auszahlungen.
            </p>

            <form method="post">
                <label for="amount">Betrag</label>
                <input type="number" step="0.01" min="-10000" max="10000" id="amount" name="amount" placeholder="Betrag" value="0.00"/>

                <?php if ($answer !== null): ?>
                    <p class="text-muted">Antwort vom Server:<br /><b><?php echo $answer; ?></b></p>
                <?php endif; ?>

                <input type="submit" name="submit" id="submit" value="Absenden" />
            </form>
            <script>

            </script>
        </main>
    </body>

    </html>

<?php
