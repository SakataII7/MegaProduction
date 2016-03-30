using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;
using MegaProductionDBLIB;

namespace MegaProduction
{
    /// <summary>
    /// Logique d'interaction pour PartenaireWindow.xaml
    /// </summary>
    public partial class PartenaireWindow : Window
    {
        public PartenaireWindow(MegaCastingsEntities context)
        {
            InitializeComponent();
        }
    }
}
